package com.musinsa.product.category

import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.dto.ProductCategoryResponse
import com.musinsa.product.entity.ProductCategory

object DummyProductCategory {
    private const val id: Long = 1L
    private const val name: String = "스니커즈"
    private const val iconImageUrl: String = "https://image.msscdn.net/images/category_img/men/ITEM_103004_17406371131039_big.png"
    private const val createdAt: Long = 0L
    private const val updatedAt: Long = 0L

    private const val parentId: Long = 2L
    private const val parentName: String = "신발"
    private const val parentIconImageUrl: String = "https://image.msscdn.net/images/category_img/store/ITEM_103_17459760462754_big.png"
    private const val parentCreatedAt: Long = 0L
    private const val parentUpdatedAt: Long = 0L

    private val dummyProductCategory: ProductCategory by lazy {
        ProductCategory(
            name = name,
            iconImageUrl = iconImageUrl,
        ).also {
            it.id = id
        }
    }

    private val dummyParentProductCategory: ProductCategory by lazy {
        ProductCategory(
            name = parentName,
            iconImageUrl = parentIconImageUrl
        ).also {
            it.id = parentId
        }
    }

    private val dummyProductCategoryResponse: ProductCategoryResponse by lazy {
        ProductCategoryResponse(
            id = id,
            parentId = null,
            name = name,
            iconImageUrl = iconImageUrl,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    private val dummyCreateProductCategoryRequest: CreateProductCategoryRequest by lazy {
        CreateProductCategoryRequest(
            name = name,
            iconImageUrl = iconImageUrl
        )
    }

    fun toEntity(): ProductCategory = dummyProductCategory

    fun toParentEntity(): ProductCategory = dummyParentProductCategory

    fun toResponse(): ProductCategoryResponse = dummyProductCategoryResponse

    fun toCreateRequest(): CreateProductCategoryRequest = dummyCreateProductCategoryRequest
}
