package com.musinsa.product.repository

import com.musinsa.product.entity.ProductCategory
import org.springframework.stereotype.Component

@Component
class ProductCategoryRepositoryFacade(
    private val repository: ProductCategoryRepository
) {

    fun save(productCategory: ProductCategory): ProductCategory {
        return repository.save(productCategory)
    }
}
