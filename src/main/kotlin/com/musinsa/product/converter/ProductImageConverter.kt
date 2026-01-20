package com.musinsa.product.converter

import com.musinsa.product.dto.CreateProductImageRequest
import com.musinsa.product.entity.ProductImage
import org.springframework.stereotype.Component

@Component
class ProductImageConverter {

    fun toEntity(request: CreateProductImageRequest): ProductImage {
        return ProductImage(
            path = request.path,
            width = request.width,
            height = request.height,
            sortOrder = request.sortOrder
        )
    }

    fun toEntityInBatch(requests: List<CreateProductImageRequest>): List<ProductImage> {
        return requests.map { request -> toEntity(request) }
    }
}
