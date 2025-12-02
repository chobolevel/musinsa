package com.musinsa.product.service

import com.musinsa.product.dto.CreateProductCategoryRequest

interface ProductCategoryService {

    fun createProductCategory(request: CreateProductCategoryRequest): Long
}
