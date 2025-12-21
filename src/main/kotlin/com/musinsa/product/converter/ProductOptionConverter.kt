package com.musinsa.product.converter

import com.musinsa.product.dto.CreateProductOptionRequest
import com.musinsa.product.entity.ProductOption
import org.springframework.stereotype.Component

@Component
class ProductOptionConverter {

    fun toEntity(request: CreateProductOptionRequest): ProductOption {
        return ProductOption(
            name = request.name,
            sortOrder = request.sortOrder,
            isRequired = request.isRequired,
        )
    }

    fun toEntityInBatch(requests: List<CreateProductOptionRequest>): List<ProductOption> {
        return requests.map { request -> toEntity(request) }
    }
}
