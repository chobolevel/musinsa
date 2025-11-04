package com.musinsa.brand.dto

import com.musinsa.brand.vo.BrandUpdateMask
import com.musinsa.common.vo.NationType
import jakarta.validation.constraints.Size

data class UpdateBrandRequest(
    val name: String?,
    val englishName: String?,
    val yearOfLaunch: Int?,
    val nation: NationType?,
    val description: String?,
    @field:Size(min = 1)
    val updateMask: List<BrandUpdateMask>
)
