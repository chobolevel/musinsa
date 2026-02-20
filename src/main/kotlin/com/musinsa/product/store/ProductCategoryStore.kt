package com.musinsa.product.store

import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.repository.ProductCategoryRepository
import org.springframework.stereotype.Component

@Component
class ProductCategoryStore(
    private val productCategoryRepository: ProductCategoryRepository
) {

    fun save(productCategory: ProductCategory): ProductCategory {
        return productCategoryRepository.save(productCategory)
    }
}
