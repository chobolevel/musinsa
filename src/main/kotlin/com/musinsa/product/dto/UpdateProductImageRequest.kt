package com.musinsa.product.dto

import com.musinsa.product.vo.ProductImageUpdateMask
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UpdateProductImageRequest(
    @field:NotNull
    val id: Long,
    val path: String?,
    val width: Int?,
    val height: Int?,
    val sortOrder: Int?,
    @field:Size(min = 1)
    @field:Valid
    val updateMask: List<ProductImageUpdateMask>
) : ProductImageCommand
