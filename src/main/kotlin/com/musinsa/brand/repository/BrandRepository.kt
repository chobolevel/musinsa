package com.musinsa.brand.repository

import com.musinsa.brand.entity.Brand
import org.springframework.data.jpa.repository.JpaRepository

interface BrandRepository : JpaRepository<Brand, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): Brand?
}
