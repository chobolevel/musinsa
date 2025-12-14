package com.musinsa.product.converter

import com.musinsa.product.dto.CreateProductRequest
import com.musinsa.product.entity.Product
import org.springframework.stereotype.Component

@Component
class ProductConverter {

    fun toEntity(request: CreateProductRequest): Product {
        return Product(
            name = request.name,
            description = request.description,
            standardPrice = request.standardPrice,
            sortOrder = request.sortOrder ?: 10
        )
    }
}
