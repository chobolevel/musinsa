package com.musinsa.product.repository

import com.musinsa.product.entity.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository

interface ProductCategoryRepository : JpaRepository<ProductCategory, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): ProductCategory?
}
