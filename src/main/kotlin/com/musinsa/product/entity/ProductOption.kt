package com.musinsa.product.entity

import com.musinsa.common.entity.BaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OrderBy
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.hibernate.envers.Audited
import org.hibernate.envers.NotAudited

@Entity
@Table(name = "product_options")
@Audited
@SQLDelete(sql = "UPDATE product_options SET is_deleted = 1 WHERE id = ?")
class ProductOption(
    @Column(nullable = false, length = 100)
    var name: String,
    @Column(nullable = false)
    var sortOrder: Int,
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    var isRequired: Boolean
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @NotAudited
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product? = null
        protected set

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    var isDeleted: Boolean = false

    @OneToMany(mappedBy = "productOption", cascade = [CascadeType.ALL], orphanRemoval = true)
    @NotAudited
    @SQLRestriction("is_deleted = 0")
    @OrderBy("sort_order asc")
    private val _productOptionValues: MutableList<ProductOptionValue> = mutableListOf()
    val productOptionValues: List<ProductOptionValue>
        get() = _productOptionValues

    // JPA에 이 필드는 영속성 대상이 아님을 명시
    @Transient
    private val pendingProductOptionValues: MutableList<ProductOptionValue> = mutableListOf()

    fun attachProductOptionValues(productOptionValues: List<ProductOptionValue>) {
        this.pendingProductOptionValues.addAll(productOptionValues)
    }

    fun flushProductOptionValues() {
        this.pendingProductOptionValues.forEach {
                productOptionValue ->
            this.addProductOptionValue(productOptionValue = productOptionValue)
        }
        this.pendingProductOptionValues.clear()
    }

    /* ==============================
     * 생성 시 불변식 검증
     * ============================== */
    init {
        validate()
    }

    private fun validate() {
        require(sortOrder > 0) { "상품 옵션 정렬 순서(sort_order)은(는) 0보다 커야 합니다." }
    }

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignProduct(product: Product) {
        if (this.product != product) {
            this.product = product
        }
    }

    fun addProductOptionValue(productOptionValue: ProductOptionValue) {
        if (!this._productOptionValues.contains(productOptionValue)) {
            this._productOptionValues.add(productOptionValue)
            productOptionValue.assignProductOption(productOption = this)
        }
    }

    /* ==============================
     * 상태 변경 메서드
     * ============================== */
    fun delete() {
        this.isDeleted = true
    }

    fun deleteProductOptionValueInBatch(productOptionValueIds: List<Long>) {
        this._productOptionValues.removeIf {
            it.id in productOptionValueIds
        }
    }
}
