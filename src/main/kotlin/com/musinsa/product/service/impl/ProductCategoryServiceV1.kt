package com.musinsa.product.service.impl

import com.musinsa.product.converter.ProductCategoryConverter
import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.repository.ProductCategoryRepositoryFacade
import com.musinsa.product.service.ProductCategoryService
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
}
