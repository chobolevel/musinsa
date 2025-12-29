package com.musinsa.product.dto

import jakarta.validation.constraints.NotEmpty

data class CreateProductCategoryRequest(
    val parentId: Long?,
    @field:NotEmpty
    val name: String,
    @field:NotEmpty
    val iconImagePath: String
)
