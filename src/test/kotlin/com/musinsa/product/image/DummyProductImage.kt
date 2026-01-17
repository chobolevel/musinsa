package com.musinsa.product.image

import com.musinsa.product.entity.ProductImage

object DummyProductImage {
    private const val id: Long = 1L
    private const val path: String = "/image/2025/06/02/c92caa4f-4240-4ddc-8c51-f235845103b9.jpeg"
    private val width: Int = 100
    private val height: Int = 100
    private val sortOrder: Int = 0

    private val dummyProductImage: ProductImage by lazy {
        ProductImage(
            path = path,
            width = width,
            height = height,
            sortOrder = sortOrder,
        )
    }

    fun toEntity(): ProductImage = dummyProductImage
}
