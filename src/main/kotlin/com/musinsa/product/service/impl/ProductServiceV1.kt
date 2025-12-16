package com.musinsa.product.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.converter.ProductConverter
import com.musinsa.product.dto.CreateProductRequest
import com.musinsa.product.dto.ProductResponse
import com.musinsa.product.dto.UpdateProductRequest
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.repository.ProductBrandRepositoryFacade
import com.musinsa.product.repository.ProductCategoryRepositoryFacade
import com.musinsa.product.repository.ProductQueryFilter
import com.musinsa.product.repository.ProductRepositoryFacade
import com.musinsa.product.service.ProductService
import com.musinsa.product.updater.ProductUpdater
import com.musinsa.product.vo.ProductOrderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductServiceV1(
    private val productRepository: ProductRepositoryFacade,
    private val productBrandRepository: ProductBrandRepositoryFacade,
    private val productCategoryRepository: ProductCategoryRepositoryFacade,
    private val converter: ProductConverter,
    private val updater: ProductUpdater
) : ProductService {

    @Transactional
    override fun createProduct(request: CreateProductRequest): Long {
        val product: Product = converter.toEntity(request = request).also { product ->
            val productBrand: ProductBrand = productBrandRepository.findById(id = request.productBrandId)
            product.assignProductBrand(productBrand = productBrand)

            val productCategory: ProductCategory = productCategoryRepository.findById(id = request.productCategoryId)
            product.assignProductCategory(productCategory = productCategory)
        }
        return productRepository.save(product = product).id!!
    }

    @Transactional(readOnly = true)
    override fun getProducts(
        queryFilter: ProductQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductOrderType>
    ): PaginationResponse {
        val products: List<Product> = productRepository.searchProducts(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = productRepository.searchProductsCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(products = products),
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    override fun getProduct(productId: Long): ProductResponse {
        val product: Product = productRepository.findById(id = productId)
        return converter.toResponse(product = product)
    }

    @Transactional
    override fun updateProduct(
        productId: Long,
        request: UpdateProductRequest
    ): Long {
        val product: Product = productRepository.findById(id = productId)
        updater.markAsUpdate(
            request = request,
            product = product
        )
        return productId
    }
}
