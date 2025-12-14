package com.musinsa.product.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class CreateProductRequest(
    @field:NotNull
    val productBrandId: Long,
    @field:NotNull
    val productCategoryId: Long,
    @field:NotEmpty
    val name: String,
    val description: String?,
    @field:NotNull
    val standardPrice: Int,
    var order: Int?
)
