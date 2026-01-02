package com.musinsa.product.converter

import com.musinsa.product.dto.CreateProductOptionValueRequest
import com.musinsa.product.entity.ProductOptionValue
import org.springframework.stereotype.Component

@Component
class ProductOptionValueConverter {

    fun toEntity(request: CreateProductOptionValueRequest): ProductOptionValue {
        return ProductOptionValue(
            name = request.name,
            sortOrder = request.sortOrder
        )
    }
}
