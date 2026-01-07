package com.musinsa.product.dto

import com.musinsa.product.vo.ProductOptionValueUpdateMask
import jakarta.validation.constraints.Size

data class UpdateProductOptionValueRequest(
    val id: Long?,
    val name: String?,
    val sortOrder: Int?,
    @field:Size(min = 1)
    val updateMask: List<ProductOptionValueUpdateMask>
)
