package com.musinsa.product.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.dto.ProductCategoryResponse
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

    fun toResponse(productCategory: ProductCategory): ProductCategoryResponse {
        return ProductCategoryResponse(
            id = productCategory.id!!,
            parentId = productCategory.parent?.id,
            name = productCategory.name,
            iconImageUrl = productCategory.iconImageUrl,
            createdAt = productCategory.createdAt!!.toMillis(),
            updatedAt = productCategory.updatedAt!!.toMillis()
        )
    }

    fun toResponses(productCategories: List<ProductCategory>): List<ProductCategoryResponse> {
        return productCategories.map { toResponse(it) }
    }
}
