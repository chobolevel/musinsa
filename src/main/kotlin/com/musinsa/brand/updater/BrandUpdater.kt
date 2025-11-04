package com.musinsa.brand.updater

import com.musinsa.brand.dto.UpdateBrandRequest
import com.musinsa.brand.entity.Brand
import com.musinsa.brand.vo.BrandUpdateMask
import org.springframework.stereotype.Component

@Component
class BrandUpdater {

    fun markAsUpdate(request: UpdateBrandRequest, brand: Brand): Brand {
        request.updateMask.forEach {
            when (it) {
                BrandUpdateMask.NAME -> brand.name = request.name!!
                BrandUpdateMask.ENGLISH_NAME -> brand.englishName = request.englishName
                BrandUpdateMask.YEAR_OF_LAUNCH -> brand.yearOfLaunch = request.yearOfLaunch!!
                BrandUpdateMask.NATION -> brand.nation = request.nation!!
                BrandUpdateMask.DESCRIPTION -> brand.description = request.description!!
            }
        }
        return brand
    }
}
