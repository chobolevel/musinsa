package com.musinsa.product.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.converter.ProductBrandConverter
import com.musinsa.product.dto.CreateProductBrandRequest
import com.musinsa.product.dto.ProductBrandResponse
import com.musinsa.product.dto.UpdateProductBrandRequest
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.repository.ProductBrandQueryFilter
import com.musinsa.product.repository.ProductBrandRepositoryFacade
import com.musinsa.product.service.ProductBrandService
import com.musinsa.product.updater.ProductBrandUpdater
import com.musinsa.product.vo.ProductBrandOrderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductBrandServiceV1(
    private val repository: ProductBrandRepositoryFacade,
    private val converter: ProductBrandConverter,
    private val updater: ProductBrandUpdater
) : ProductBrandService {

    @Transactional
    override fun createBrand(request: CreateProductBrandRequest): Long {
        val productBrand: ProductBrand = converter.toEntity(request = request)
        return repository.save(productBrand = productBrand).id!!
    }

    override fun getBrands(
        queryFilter: ProductBrandQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductBrandOrderType>
    ): PaginationResponse {
        val productBrands: List<ProductBrand> = repository.searchProductBrands(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = repository.searchProductBrandsCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(productBrands = productBrands),
            totalCount = totalCount
        )
    }

    override fun getBrand(id: Long): ProductBrandResponse {
        val productBrand: ProductBrand = repository.findById(id = id)
        return converter.toResponse(productBrand = productBrand)
    }

    @Transactional
    override fun updateBrand(id: Long, request: UpdateProductBrandRequest): Long {
        val productBrand: ProductBrand = repository.findById(id = id)
        updater.markAsUpdate(
            request = request,
            productBrand = productBrand
        )
        return id
    }

    @Transactional
    override fun deleteBrand(id: Long): Boolean {
        val productBrand: ProductBrand = repository.findById(id = id)
        productBrand.delete()
        return true
    }
}
