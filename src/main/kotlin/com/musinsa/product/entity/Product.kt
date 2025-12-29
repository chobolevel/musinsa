package com.musinsa.product.entity

import com.musinsa.common.entity.Audit
import com.musinsa.product.vo.ProductSaleStatus
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OrderBy
import jakarta.persistence.Table
import org.hibernate.annotations.SQLRestriction
import org.hibernate.envers.NotAudited

@Entity
@Table(name = "products")
class Product(
    @Column(nullable = false, length = 100)
    var name: String,
    @Column(nullable = true, length = 255)
    var description: String?,
    @Column(nullable = false)
    var standardPrice: Int,
    @Column(nullable = false)
    var sortOrder: Int = 10,
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_brand_id")
    var productBrand: ProductBrand? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    var productCategory: ProductCategory? = null

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var saleStatus: ProductSaleStatus = ProductSaleStatus.DISCONTINUED

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    var isDeleted: Boolean = false

    @NotAudited
    @OneToMany(mappedBy = "product", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    @SQLRestriction("is_deleted = false")
    @OrderBy("sort_order asc")
    val productOptions: MutableList<ProductOption> = mutableListOf()

    init {
        validate()
    }

    /* ==============================
     * 생성 시 불변식 검증
     * ============================== */
    private fun validate() {
        // 정책이 변경되어도 변경되지 않는 검증 로직만 작성되어야 함
        // throw IllegalArgumentException
        require(standardPrice > 0) { "기준가(standard_price)은(는) 0보다 커야 합니다." }
        require(sortOrder > 0) { "상품 정렬 순서(sort_order)은(는) 0보다 커야 합니다." }
    }

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignProductBrand(productBrand: ProductBrand) {
        if (this.productBrand != productBrand) {
            this.productBrand = productBrand
        }
    }

    fun assignProductCategory(productCategory: ProductCategory) {
        if (this.productCategory != productCategory) {
            this.productCategory = productCategory
        }
    }

    fun addProductOption(productOption: ProductOption) {
        if (!this.productOptions.contains(productOption)) {
            this.productOptions.add(productOption)
            productOption.assignProduct(product = this)
        }
    }

    /* ==============================
     * 상태 변경 메서드
     * ============================== */
    fun delete() {
        this.isDeleted = true
    }
}
