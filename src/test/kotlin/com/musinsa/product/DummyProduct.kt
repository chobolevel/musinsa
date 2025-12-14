package com.musinsa.product

import com.musinsa.product.dto.CreateProductRequest
import com.musinsa.product.entity.Product

object DummyProduct {
    private const val id: Long = 1L
    private const val name: String = "젤-K1011 - 블랙:퓨어실버 "
    private val description: String? = null
    private const val standardPrice: Int = 169_000

    private val dummyProduct: Product by lazy {
        Product(
            name = name,
            description = description,
            standardPrice = standardPrice,
        ).also { it.id = id }
    }

    private val dummyCreateRequest: CreateProductRequest by lazy {
        CreateProductRequest(
            productBrandId = 1L,
            productCategoryId = 1L,
            name = name,
            description = description,
            standardPrice = standardPrice,
            sortOrder = null
        )
    }

    fun toEntity(): Product = dummyProduct

    fun toCreateRequest(): CreateProductRequest = dummyCreateRequest
}
