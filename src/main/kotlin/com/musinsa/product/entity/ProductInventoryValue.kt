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

@Entity
@Table(name = "product_inventory_values")
class ProductInventoryValue : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_inventory_id")
    private var _productInventory: ProductInventory? = null
    val productInventory: ProductInventory?
        get() = _productInventory

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_value_id")
    private var _productOptionValue: ProductOptionValue? = null
    val productOptionValue: ProductOptionValue?
        get() = _productOptionValue

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignProductInventory(productInventory: ProductInventory) {
        if (this._productInventory != productInventory) {
            this._productInventory = productInventory
        }
    }

    fun assignProductOptionValue(productOptionValue: ProductOptionValue) {
        if (this._productOptionValue != productOptionValue) {
            this._productOptionValue = productOptionValue
        }
    }
}
