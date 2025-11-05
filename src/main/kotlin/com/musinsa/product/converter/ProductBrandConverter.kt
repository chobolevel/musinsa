package com.musinsa.product.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.product.dto.CreateProductBrandRequest
import com.musinsa.product.dto.ProductBrandResponse
import com.musinsa.product.entity.ProductBrand
import org.springframework.stereotype.Component

@Component
class ProductBrandConverter {

    fun toEntity(request: CreateProductBrandRequest): ProductBrand {
        return ProductBrand(
            name = request.name,
            englishName = request.englishName,
            yearOfLaunch = request.yearOfLaunch,
            nation = request.nation,
            description = request.description,
        )
    }

    fun toResponse(productBrand: ProductBrand): ProductBrandResponse {
        return ProductBrandResponse(
            id = productBrand.id!!,
            name = productBrand.name,
            englishName = productBrand.englishName,
            yearOfLaunch = productBrand.yearOfLaunch,
            nation = productBrand.nation,
            nationKoreanName = productBrand.nation.koreanName,
            description = productBrand.description,
            createdAt = productBrand.createdAt!!.toMillis(),
            updatedAt = productBrand.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(productBrands: List<ProductBrand>): List<ProductBrandResponse> {
        return productBrands.map { toResponse(it) }
    }
}
