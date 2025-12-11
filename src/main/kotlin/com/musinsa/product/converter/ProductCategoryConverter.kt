package com.musinsa.product.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.dto.ProductCategoryResponse
import com.musinsa.product.entity.ProductCategory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ProductCategoryConverter(
    @Value("\${cloud.aws.s3.imageHost}")
    private val imageHost: String,
) {

    fun toEntity(request: CreateProductCategoryRequest): ProductCategory {
        return ProductCategory(
            name = request.name,
            iconImagePath = request.iconImagePath
        )
    }

    fun toResponse(productCategory: ProductCategory): ProductCategoryResponse {
        return ProductCategoryResponse(
            id = productCategory.id!!,
            parentId = productCategory.parent?.id,
            name = productCategory.name,
            iconImagePath = productCategory.iconImagePath,
            iconImageUrl = "$imageHost${productCategory.iconImagePath}",
            createdAt = productCategory.createdAt!!.toMillis(),
            updatedAt = productCategory.updatedAt!!.toMillis()
        )
    }

    fun toResponses(productCategories: List<ProductCategory>): List<ProductCategoryResponse> {
        return productCategories.map { toResponse(it) }
    }
}
