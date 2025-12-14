package com.musinsa.product.service.impl

import com.musinsa.product.converter.ProductConverter
import com.musinsa.product.dto.CreateProductRequest
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.repository.ProductBrandRepositoryFacade
import com.musinsa.product.repository.ProductCategoryRepositoryFacade
import com.musinsa.product.service.ProductService
import com.musinsa.snap.repository.ProductRepositoryFacade
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProductServiceV1(
    private val productRepository: ProductRepositoryFacade,
    private val productBrandRepository: ProductBrandRepositoryFacade,
    private val productCategoryRepository: ProductCategoryRepositoryFacade,
    private val converter: ProductConverter,
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
}
