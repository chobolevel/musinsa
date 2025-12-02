package com.musinsa.product.converter

import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.entity.ProductCategory
import org.springframework.stereotype.Component

@Component
class ProductCategoryConverter {

    fun toEntity(request: CreateProductCategoryRequest): ProductCategory {
        return ProductCategory(
            name = request.name,
            iconImageUrl = request.iconImageUrl
        )
    }
}
