package com.musinsa.product.service

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.dto.CreateProductRequest
import com.musinsa.product.dto.ProductResponse
import com.musinsa.product.repository.ProductQueryFilter
import com.musinsa.product.vo.ProductOrderType

interface ProductService {

    fun createProduct(request: CreateProductRequest): Long

    fun getProducts(
        queryFilter: ProductQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductOrderType>,
    ): PaginationResponse

    fun getProduct(productId: Long): ProductResponse
}
