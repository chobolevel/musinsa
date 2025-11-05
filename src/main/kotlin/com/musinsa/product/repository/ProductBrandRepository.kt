package com.musinsa.product.repository

import com.musinsa.product.entity.ProductBrand
import org.springframework.data.jpa.repository.JpaRepository

interface ProductBrandRepository : JpaRepository<ProductBrand, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): ProductBrand?
}
