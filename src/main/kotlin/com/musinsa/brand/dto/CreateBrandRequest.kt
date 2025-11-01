package com.musinsa.brand.dto

import com.musinsa.common.vo.NationType
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class CreateBrandRequest(
    @field:NotEmpty
    val name: String,
    @field:NotEmpty
    val englishName: String,
    @field:NotNull
    val yearOfLaunch: Int,
    @field:NotNull
    val nation: NationType,
    @field:NotEmpty
    val description: String
)
