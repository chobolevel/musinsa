package com.musinsa.snap.repository

import com.musinsa.product.entity.Product
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryFacade(
    private val repository: ProductRepository
) {

    fun save(product: Product): Product {
        return repository.save(product)
    }
}
