package com.musinsa.product.repository

import com.musinsa.product.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>
