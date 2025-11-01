package com.musinsa.brand.service

import com.musinsa.brand.dto.CreateBrandRequest

interface BrandService {

    fun createBrand(request: CreateBrandRequest): Long
}
