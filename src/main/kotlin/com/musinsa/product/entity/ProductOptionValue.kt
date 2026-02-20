package com.musinsa.product.entity

import com.musinsa.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.envers.Audited

@Entity
@Table(name = "product_option_values")
@Audited
class ProductOptionValue(
    @Column(nullable = false, length = 100)
    var name: String,
    @Column(nullable = false)
    var sortOrder: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_id")
    private var _productOption: ProductOption? = null
    val productOption: ProductOption?
        get() = _productOption

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    var isDeleted: Boolean = false

    /* ==============================
     * 생성 시 불변식 검증
     * ============================== */
    init {
        require(sortOrder > 0) { "상품 옵션 값 정렬 순서(sort_order)은(는) 반드시 0보다 커야 합니다." }
    }

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignProductOption(productOption: ProductOption) {
        if (this._productOption != productOption) {
            this._productOption = productOption
        }
    }

    /* ==============================
     * 상태 변경 메서드
     * ============================== */
    fun delete() {
        this.isDeleted = true
    }
}
