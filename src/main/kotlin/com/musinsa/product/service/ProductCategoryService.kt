package com.musinsa.product.service

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.dto.ProductCategoryResponse
import com.musinsa.product.dto.UpdateProductCategoryRequest
import com.musinsa.product.repository.ProductCategoryQueryFilter
import com.musinsa.product.vo.ProductCategoryOrderType

interface ProductCategoryService {

    fun createProductCategory(request: CreateProductCategoryRequest): Long

    fun getProductCategories(
        queryFilter: ProductCategoryQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductCategoryOrderType>
    ): PaginationResponse

    fun getProductCategory(productCategoryId: Long): ProductCategoryResponse

    fun updateProductCategory(productCategoryId: Long, request: UpdateProductCategoryRequest): Long
}
