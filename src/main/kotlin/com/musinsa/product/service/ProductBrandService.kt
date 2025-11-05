package com.musinsa.product.service

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.dto.CreateProductBrandRequest
import com.musinsa.product.dto.ProductBrandResponse
import com.musinsa.product.dto.UpdateProductBrandRequest
import com.musinsa.product.repository.ProductBrandQueryFilter
import com.musinsa.product.vo.ProductBrandOrderType

interface ProductBrandService {

    fun createBrand(request: CreateProductBrandRequest): Long

    fun getBrands(
        queryFilter: ProductBrandQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductBrandOrderType>
    ): PaginationResponse

    fun getBrand(id: Long): ProductBrandResponse

    fun updateBrand(id: Long, request: UpdateProductBrandRequest): Long

    fun deleteBrand(id: Long): Boolean
}
