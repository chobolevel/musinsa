package com.musinsa.brand.service.impl

import com.musinsa.brand.converter.BrandConverter
import com.musinsa.brand.dto.CreateBrandRequest
import com.musinsa.brand.entity.Brand
import com.musinsa.brand.repository.BrandRepositoryFacade
import com.musinsa.brand.service.BrandService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrandServiceV1(
    private val repository: BrandRepositoryFacade,
    private val converter: BrandConverter
) : BrandService {

    @Transactional
    override fun createBrand(request: CreateBrandRequest): Long {
        val brand: Brand = converter.toEntity(request = request)
        return repository.save(brand = brand).id!!
    }
}
