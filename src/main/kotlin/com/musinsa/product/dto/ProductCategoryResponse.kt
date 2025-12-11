package com.musinsa.product.dto

data class ProductCategoryResponse(
    val id: Long,
    val parentId: Long?,
    val name: String,
    val iconImagePath: String,
    val iconImageUrl: String,
    val createdAt: Long,
    val updatedAt: Long
)
