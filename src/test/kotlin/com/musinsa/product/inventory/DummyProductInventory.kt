package com.musinsa.product.inventory

import com.musinsa.product.entity.ProductInventory

object DummyProductInventory {
    private const val id: Long = 1L
    private const val stock: Int = 100

    private val dummyProductInventory: ProductInventory by lazy {
        ProductInventory(
            stock = stock
        ).also { it.id = id }
    }

    fun toEntity(): ProductInventory = dummyProductInventory
}
