package com.musinsa.product.dto

import com.musinsa.product.vo.ProductOptionUpdateMask
import jakarta.validation.constraints.Size

data class UpdateProductOptionRequest(
    val id: Long?,
    val name: String?,
    val sortOrder: Int?,
    val isRequired: Boolean?,
    val values: List<UpdateProductOptionValueRequest>?,
    @field:Size(min = 1)
    val updateMask: List<ProductOptionUpdateMask>
)
