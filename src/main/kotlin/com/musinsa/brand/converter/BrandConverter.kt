package com.musinsa.brand.converter

import com.musinsa.brand.dto.CreateBrandRequest
import com.musinsa.brand.entity.Brand
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
}
