package com.musinsa.product.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.product.dto.CreateProductRequest
import com.musinsa.product.dto.ProductResponse
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.ProductCategory
import org.springframework.stereotype.Component

@Component
class ProductConverter {

    fun toEntity(request: CreateProductRequest): Product {
        return Product(
            name = request.name,
            description = request.description,
            standardPrice = request.standardPrice,
            sortOrder = request.sortOrder ?: 10
        )
    }

    fun toResponse(product: Product): ProductResponse {
        val productBrand: ProductBrand = product.productBrand!!
        val productCategory: ProductCategory = product.productCategory!!
        return ProductResponse(
            id = product.id!!,
            productBrandId = productBrand.id!!,
            productBrandName = productBrand.name,
            productCategoryId = productCategory.id!!,
            productCategoryName = productCategory.name,
            name = product.name,
            description = product.description,
            standardPrice = product.standardPrice,
            saleStatus = product.saleStatus,
            createdAt = product.createdAt!!.toMillis(),
            updatedAt = product.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(products: List<Product>): List<ProductResponse> {
        return products.map { toResponse(it) }
    }
}
