package com.musinsa.product

import com.musinsa.product.brand.DummyProductBrand
import com.musinsa.product.category.DummyProductCategory
import com.musinsa.product.dto.CreateProductRequest
import com.musinsa.product.dto.ProductResponse
import com.musinsa.product.dto.UpdateProductRequest
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.vo.ProductSaleStatus
import com.musinsa.product.vo.ProductUpdateMask

object DummyProduct {
    private const val id: Long = 1L
    private const val name: String = "젤-K1011 - 블랙:퓨어실버"
    private val description: String? = null
    private const val standardPrice: Int = 169_000
    private const val createdAt: Long = 0L
    private const val updatedAt: Long = 0L

    private val dummyProduct: Product by lazy {
        Product(
            name = name,
            description = description,
            standardPrice = standardPrice,
        ).also { it.id = id }
    }

    private val dummyProductResponse: ProductResponse by lazy {
        val dummyProductBrand: ProductBrand = DummyProductBrand.toEntity()
        val dummyProductCategory: ProductCategory = DummyProductCategory.toEntity()
        ProductResponse(
            id = id,
            productBrandId = dummyProductBrand.id!!,
            productBrandName = dummyProductBrand.name,
            productCategoryId = dummyProductCategory.id!!,
            productCategoryName = dummyProductCategory.name,
            name = name,
            description = description,
            standardPrice = standardPrice,
            saleStatus = ProductSaleStatus.SALE,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    private val dummyCreateRequest: CreateProductRequest by lazy {
        CreateProductRequest(
            productBrandId = 1L,
            productCategoryId = 1L,
            name = name,
            description = description,
            standardPrice = standardPrice,
            sortOrder = null
        )
    }

    private val dummyUpdateRequest: UpdateProductRequest by lazy {
        UpdateProductRequest(
            productBrandId = null,
            productCategoryId = null,
            name = "젤-K1011 - 블랙",
            description = null,
            standardPrice = null,
            saleStatus = null,
            sortOrder = null,
            updateMask = listOf(ProductUpdateMask.NAME)
        )
    }

    fun toEntity(): Product = dummyProduct

    fun toResponse(): ProductResponse = dummyProductResponse

    fun toCreateRequest(): CreateProductRequest = dummyCreateRequest

    fun toUpdateRequest(): UpdateProductRequest = dummyUpdateRequest
}
