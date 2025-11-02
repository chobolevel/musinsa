package com.musinsa.brand.converter

import com.musinsa.brand.dto.BrandResponse
import com.musinsa.brand.dto.CreateBrandRequest
import com.musinsa.brand.entity.Brand
import com.musinsa.common.extension.toMillis
import org.springframework.stereotype.Component

@Component
class BrandConverter {

    fun toEntity(request: CreateBrandRequest): Brand {
        return Brand(
            name = request.name,
            englishName = request.englishName,
            yearOfLaunch = request.yearOfLaunch,
            nation = request.nation,
            description = request.description,
        )
    }

    fun toResponse(brand: Brand): BrandResponse {
        return BrandResponse(
            id = brand.id!!,
            name = brand.name,
            englishName = brand.englishName,
            yearOfLaunch = brand.yearOfLaunch,
            nation = brand.nation,
            nationKoreanName = brand.nation.koreanName,
            description = brand.description,
            createdAt = brand.createdAt!!.toMillis(),
            updatedAt = brand.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(brands: List<Brand>): List<BrandResponse> {
        return brands.map { toResponse(it) }
    }
}
