package com.musinsa.product.converter

import com.musinsa.product.entity.ProductInventory
import com.musinsa.product.entity.ProductInventoryValue
import com.musinsa.product.entity.ProductOption
import com.musinsa.product.entity.ProductOptionValue
import org.springframework.stereotype.Component
import kotlin.collections.forEach
import kotlin.collections.plus

@Component
class ProductInventoryConverter {

    fun toEntityInBatch(productOptions: List<ProductOption>): List<ProductInventory> {
        val combinations: List<List<ProductOptionValue>> = productOptions.fold(listOf(emptyList())) { acc, productOption ->
            acc.flatMap { prefix ->
                productOption.productOptionValues.map { prefix + it }
            }
        }
        return combinations.map { productOptionValues ->
            val productInventory = ProductInventory(
                stock = 100
            )
            productOptionValues.forEach { productOptionValue ->
                val productInventoryValue = ProductInventoryValue().also {
                    it.assignProductOptionValue(productOptionValue = productOptionValue)
                }
                productInventory.addProductInventoryValue(productInventoryValue = productInventoryValue)
            }
            productInventory
        }
    }
}
