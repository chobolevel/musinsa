package com.musinsa.product.option

import com.musinsa.product.dto.CreateProductOptionValueRequest
import com.musinsa.product.entity.ProductOptionValue

object DummyProductOptionValue {
    private const val id: Long = 1L
    private const val name: String = "블랙"
    private const val sortOrder: Int = 1
    private const val createdAt: Long = 0L
    private const val updatedAt: Long = 0L

    private val dummyProductOptionValue: ProductOptionValue by lazy {
        ProductOptionValue(
            name = name,
            sortOrder = sortOrder,
        ).also { it.id = id }
    }

    private val dummyCreateRequest: CreateProductOptionValueRequest by lazy {
        CreateProductOptionValueRequest(
            name = name,
            sortOrder = sortOrder,
        )
    }

    fun toEntity(): ProductOptionValue = dummyProductOptionValue

    fun toCreateRequest(): CreateProductOptionValueRequest = dummyCreateRequest
}
