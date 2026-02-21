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
    var productInventory: ProductInventory? = null
        protected set

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_value_id")
    var productOptionValue: ProductOptionValue? = null
        protected set

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignProductInventory(productInventory: ProductInventory) {
        if (this.productInventory != productInventory) {
            this.productInventory = productInventory
        }
    }

    fun assignProductOptionValue(productOptionValue: ProductOptionValue) {
        if (this.productOptionValue != productOptionValue) {
            this.productOptionValue = productOptionValue
        }
    }
}
