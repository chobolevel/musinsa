package com.musinsa.brand.service.impl

import com.musinsa.brand.converter.BrandConverter
import com.musinsa.brand.dto.BrandResponse
import com.musinsa.brand.dto.CreateBrandRequest
import com.musinsa.brand.dto.UpdateBrandRequest
import com.musinsa.brand.entity.Brand
import com.musinsa.brand.repository.BrandQueryFilter
import com.musinsa.brand.repository.BrandRepositoryFacade
import com.musinsa.brand.service.BrandService
import com.musinsa.brand.updater.BrandUpdater
import com.musinsa.brand.vo.BrandOrderType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrandServiceV1(
    private val repository: BrandRepositoryFacade,
    private val converter: BrandConverter,
    private val updater: BrandUpdater
) : BrandService {

    @Transactional
    override fun createBrand(request: CreateBrandRequest): Long {
        val brand: Brand = converter.toEntity(request = request)
        return repository.save(brand = brand).id!!
    }

    override fun getBrands(
        queryFilter: BrandQueryFilter,
        pagination: Pagination,
        orderTypes: List<BrandOrderType>
    ): PaginationResponse {
        val brands: List<Brand> = repository.searchBrands(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = repository.searchBrandsCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(brands = brands),
            totalCount = totalCount
        )
    }

    override fun getBrand(id: Long): BrandResponse {
        val brand: Brand = repository.findById(id = id)
        return converter.toResponse(brand = brand)
    }

    @Transactional
    override fun updateBrand(id: Long, request: UpdateBrandRequest): Long {
        val brand: Brand = repository.findById(id = id)
        updater.markAsUpdate(
            request = request,
            brand = brand
        )
        return id
    }

    @Transactional
    override fun deleteBrand(id: Long): Boolean {
        val brand: Brand = repository.findById(id = id)
        brand.delete()
        return true
    }
}
