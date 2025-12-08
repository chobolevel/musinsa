package com.musinsa.product.dto

import com.musinsa.product.vo.ProductCategoryUpdateMask
import jakarta.validation.constraints.Size

data class UpdateProductCategoryRequest(
    val parentId: Long?,
    val name: String?,
    val iconImageUrl: String?,
    @field:Size(min = 1)
    val updateMask: List<ProductCategoryUpdateMask>
)
