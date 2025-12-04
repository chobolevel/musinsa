package com.musinsa.product.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.converter.ProductCategoryConverter
import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.repository.ProductCategoryQueryFilter
import com.musinsa.product.repository.ProductCategoryRepositoryFacade
import com.musinsa.product.service.ProductCategoryService
import com.musinsa.product.vo.ProductCategoryOrderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCategoryServiceV1(
    private val repository: ProductCategoryRepositoryFacade,
    private val converter: ProductCategoryConverter,
) : ProductCategoryService {

    @Transactional
    override fun createProductCategory(request: CreateProductCategoryRequest): Long {
        val productCategory: ProductCategory = converter.toEntity(request = request)
        return repository.save(productCategory = productCategory).id!!
    }

    @Transactional(readOnly = true)
    override fun getProductCategories(
        queryFilter: ProductCategoryQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductCategoryOrderType>
    ): PaginationResponse {
        val productCategories: List<ProductCategory> = repository.searchProductCategories(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = repository.searchProductCategoriesCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponses(productCategories = productCategories),
            totalCount = totalCount,
        )
    }
}
