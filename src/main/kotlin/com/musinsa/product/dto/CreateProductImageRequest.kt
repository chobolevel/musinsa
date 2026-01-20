package com.musinsa.product.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class CreateProductImageRequest(
    @field:NotEmpty
    val path: String,
    @field:NotNull
    val height: Int,
    @field:NotNull
    val width: Int,
    @field:NotNull
    val sortOrder: Int,
)
