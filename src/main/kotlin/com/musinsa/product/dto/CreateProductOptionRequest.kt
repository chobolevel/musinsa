package com.musinsa.product.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CreateProductOptionRequest(
    @field:NotEmpty
    val name: String,
    @field:NotNull
    val sortOrder: Int,
    @field:NotNull
    val isRequired: Boolean,
    @field:Size(min = 1)
    val values: List<CreateProductOptionValueRequest>
) : ProductOptionCommand
