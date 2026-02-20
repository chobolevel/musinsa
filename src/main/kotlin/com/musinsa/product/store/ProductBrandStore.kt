package com.musinsa.product.store

import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.repository.ProductBrandRepository
import org.springframework.stereotype.Component

@Component
class ProductBrandStore(
    private val productBrandRepository: ProductBrandRepository
) {

    fun save(productBrand: ProductBrand): ProductBrand {
        return productBrandRepository.save(productBrand)
    }
}
