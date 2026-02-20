package com.musinsa.product.store

import com.musinsa.product.entity.Product
import com.musinsa.product.repository.ProductRepository
import org.springframework.stereotype.Component

@Component
class ProductStore(
    private val productRepository: ProductRepository
) {

    fun save(product: Product): Product {
        return productRepository.save(product)
    }
}
