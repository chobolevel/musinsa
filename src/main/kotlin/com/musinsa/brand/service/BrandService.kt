package com.musinsa.brand.service

import com.musinsa.brand.dto.CreateBrandRequest
import com.musinsa.brand.repository.BrandQueryFilter
import com.musinsa.brand.vo.BrandOrderType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse

interface BrandService {

    fun createBrand(request: CreateBrandRequest): Long

    fun getBrands(
        queryFilter: BrandQueryFilter,
        pagination: Pagination,
        orderTypes: List<BrandOrderType>
    ): PaginationResponse
}
