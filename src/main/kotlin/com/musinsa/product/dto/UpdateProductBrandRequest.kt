package com.musinsa.product.dto

import com.musinsa.common.vo.NationType
import com.musinsa.product.vo.ProductBrandUpdateMask
import jakarta.validation.constraints.Size

data class UpdateProductBrandRequest(
    val name: String?,
    val englishName: String?,
    val yearOfLaunch: Int?,
    val nation: NationType?,
    val description: String?,
    @field:Size(min = 1)
    val updateMask: List<ProductBrandUpdateMask>
)
