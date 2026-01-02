package com.musinsa.product.converter

import com.musinsa.product.dto.CreateProductOptionRequest
import com.musinsa.product.entity.ProductOption
import com.musinsa.product.entity.ProductOptionValue
import org.springframework.stereotype.Component

@Component
class ProductOptionConverter(
    private val productOptionValueConverter: ProductOptionValueConverter
) {

    fun toEntity(request: CreateProductOptionRequest): ProductOption {
        val productOption = ProductOption(
            name = request.name,
            sortOrder = request.sortOrder,
            isRequired = request.isRequired,
        )

        val productOptionValues: List<ProductOptionValue> = request.values.map(productOptionValueConverter::toEntity)
        productOption.attachProductOptionValues(productOptionValues = productOptionValues)

        return productOption
    }

    fun toEntityInBatch(requests: List<CreateProductOptionRequest>): List<ProductOption> {
        return requests.map { request -> toEntity(request) }
    }
}
