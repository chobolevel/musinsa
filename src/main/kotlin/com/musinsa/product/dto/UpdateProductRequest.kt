package com.musinsa.product.dto

import com.musinsa.product.vo.ProductSaleStatus
import com.musinsa.product.vo.ProductUpdateMask
import jakarta.validation.constraints.Size

data class UpdateProductRequest(
    val productBrandId: Long?,
    val productCategoryId: Long?,
    val name: String?,
    val description: String?,
    val standardPrice: Int?,
    val saleStatus: ProductSaleStatus?,
    val sortOrder: Int?,
    val productOptions: List<ProductOptionCommand>?,
    @field:Size(min = 1)
    val updateMask: List<ProductUpdateMask>
)
