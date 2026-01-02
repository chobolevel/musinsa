package com.musinsa.product.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class CreateProductOptionValueRequest(
    @field:NotEmpty
    val name: String,
    @field:NotNull
    val sortOrder: Int,
)
