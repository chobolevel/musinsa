package com.musinsa.product.service

import com.musinsa.product.dto.CreateProductRequest

interface ProductService {

    fun createProduct(request: CreateProductRequest): Long
}
