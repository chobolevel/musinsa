package com.musinsa.brand.dto

import com.musinsa.common.vo.NationType

data class BrandResponse(
    val id: Long,
    val name: String,
    val englishName: String?,
    val yearOfLaunch: Int,
    val nation: NationType,
    val nationKoreanName: String,
    val description: String,
    val createdAt: Long,
    val updatedAt: Long,
)
