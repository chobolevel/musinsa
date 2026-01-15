package com.musinsa.product.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

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
    var sortOrder: Int?,
    @field:Size(min = 1)
    @field:Valid
    val productOptions: List<CreateProductOptionRequest>
)
