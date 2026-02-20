package com.musinsa.product.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.assembler.ProductCategoryAssembler
import com.musinsa.product.converter.ProductCategoryConverter
import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.dto.ProductCategoryResponse
import com.musinsa.product.dto.UpdateProductCategoryRequest
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.reader.ProductCategoryQueryFilter
import com.musinsa.product.reader.ProductCategoryReader
import com.musinsa.product.service.ProductCategoryService
import com.musinsa.product.store.ProductCategoryStore
import com.musinsa.product.updater.ProductCategoryUpdater
import com.musinsa.product.vo.ProductCategoryOrderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCategoryServiceV1(
    private val productCategoryStore: ProductCategoryStore,
    private val productCategoryReader: ProductCategoryReader,
    private val converter: ProductCategoryConverter,
    private val assembler: ProductCategoryAssembler,
    private val updater: ProductCategoryUpdater
) : ProductCategoryService {

    @Transactional
    override fun createProductCategory(request: CreateProductCategoryRequest): Long {
        val baseProductCategory: ProductCategory = converter.toEntity(request = request)
        val parent: ProductCategory? = request.parentId?.let { productCategoryReader.findById(id = it) }

        val productCategory: ProductCategory = assembler.assemble(
            productCategory = baseProductCategory,
            parent = parent
        )
        return productCategoryStore.save(productCategory = productCategory).id!!
    }

    @Transactional(readOnly = true)
    override fun getProductCategories(
        queryFilter: ProductCategoryQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductCategoryOrderType>
    ): PaginationResponse {
        val productCategories: List<ProductCategory> = productCategoryReader.searchProductCategories(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = productCategoryReader.searchProductCategoriesCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponses(productCategories = productCategories),
            totalCount = totalCount,
        )
    }

    @Transactional(readOnly = true)
    override fun getProductCategory(productCategoryId: Long): ProductCategoryResponse {
        val productCategory: ProductCategory = productCategoryReader.findById(id = productCategoryId)
        return converter.toResponse(productCategory = productCategory)
    }

    @Transactional
    override fun updateProductCategory(
        productCategoryId: Long,
        request: UpdateProductCategoryRequest
    ): Long {
        val productCategory: ProductCategory = productCategoryReader.findById(id = productCategoryId)
        updater.markAsUpdate(
            productCategory = productCategory,
            request = request
        )
        return productCategoryId
    }

    @Transactional
    override fun deleteProductCategory(productCategoryId: Long): Boolean {
        val productCategory: ProductCategory = productCategoryReader.findById(id = productCategoryId)
        productCategory.delete()
        return true
    }
}
