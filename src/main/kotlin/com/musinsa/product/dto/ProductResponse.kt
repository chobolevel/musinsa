package com.musinsa.product.dto

import com.musinsa.product.vo.ProductSaleStatus

data class ProductResponse(
    val id: Long,
    val productBrandId: Long,
    val productBrandName: String,
    val productCategoryId: Long,
    val productCategoryName: String,
    val name: String,
    val description: String?,
    val standardPrice: Int,
    val saleStatus: ProductSaleStatus,
    val createdAt: Long,
    val updatedAt: Long
)
