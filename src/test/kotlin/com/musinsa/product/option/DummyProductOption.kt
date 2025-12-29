package com.musinsa.product.option

import com.musinsa.product.dto.CreateProductOptionRequest
import com.musinsa.product.entity.ProductOption

object DummyProductOption {
    private const val id: Long = 1L
    private const val name: String = "색상"
    private val sortOrder: Int = 10
    private val isRequired: Boolean = false
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummyProductOption: ProductOption by lazy {
        ProductOption(
            name = name,
            sortOrder = sortOrder,
            isRequired = isRequired,
        ).also { it.id = id }
    }

    private val dummyCreateRequest: CreateProductOptionRequest by lazy {
        CreateProductOptionRequest(
            name = name,
            sortOrder = sortOrder,
            isRequired = isRequired,
        )
    }

    fun toEntity(): ProductOption = dummyProductOption

    fun toCreateRequest(): CreateProductOptionRequest = dummyCreateRequest
}
