package com.musinsa.product.inventory

import com.musinsa.product.entity.ProductInventoryValue

object DummyProductInventoryValue {
    private const val id: Long = 1L

    private val dummyProductInventoryValue: ProductInventoryValue by lazy {
        ProductInventoryValue().also { it.id = id }
    }

    fun toEntity(): ProductInventoryValue = dummyProductInventoryValue
}
