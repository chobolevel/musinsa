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
import jakarta.persistence.Table

@Entity
@Table(name = "product_inventories")
class ProductInventory(
    @Column(nullable = false)
    var stock: Int,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private var _product: Product? = null
    val product: Product?
        get() = _product

    @OneToMany(mappedBy = "productInventory", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    private val _productInventoryValues: MutableSet<ProductInventoryValue> = HashSet()
    val productInventoryValues: Set<ProductInventoryValue>
        get() = _productInventoryValues

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignProduct(product: Product) {
        if (this._product != product) {
            this._product = product
        }
    }

    fun addProductInventoryValue(productInventoryValue: ProductInventoryValue) {
        if (!this._productInventoryValues.contains(productInventoryValue)) {
            this._productInventoryValues.add(productInventoryValue)
        }
    }
}
