package com.musinsa.product.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.assembler.ProductAssembler
import com.musinsa.product.converter.ProductConverter
import com.musinsa.product.converter.ProductImageConverter
import com.musinsa.product.converter.ProductInventoryConverter
import com.musinsa.product.converter.ProductOptionConverter
import com.musinsa.product.dto.CreateProductRequest
import com.musinsa.product.dto.ProductResponse
import com.musinsa.product.dto.UpdateProductRequest
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.entity.ProductImage
import com.musinsa.product.entity.ProductInventory
import com.musinsa.product.entity.ProductOption
import com.musinsa.product.reader.ProductBrandReader
import com.musinsa.product.reader.ProductCategoryReader
import com.musinsa.product.reader.ProductQueryFilter
import com.musinsa.product.reader.ProductReader
import com.musinsa.product.service.ProductService
import com.musinsa.product.store.ProductStore
import com.musinsa.product.updater.ProductUpdater
import com.musinsa.product.vo.ProductOrderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductServiceV1(
    private val productStore: ProductStore,
    private val productReader: ProductReader,
    private val productBrandReader: ProductBrandReader,
    private val productCategoryReader: ProductCategoryReader,
    private val converter: ProductConverter,
    private val assembler: ProductAssembler,
    private val productImageConverter: ProductImageConverter,
    private val productOptionConverter: ProductOptionConverter,
    private val productInventoryConverter: ProductInventoryConverter,
    private val updater: ProductUpdater
) : ProductService {

    @Transactional
    override fun createProduct(request: CreateProductRequest): Long {
        val baseProduct: Product = converter.toEntity(request = request)
        val productBrand: ProductBrand = productBrandReader.findById(id = request.productBrandId)
        val productCategory: ProductCategory = productCategoryReader.findById(id = request.productCategoryId)
        val productImages: List<ProductImage> = productImageConverter.toEntityInBatch(requests = request.productImages)
        val productOptions: List<ProductOption> = productOptionConverter.toEntityInBatch(requests = request.productOptions)
        val productInventories: List<ProductInventory> = productInventoryConverter.toEntityInBatch(productOptions = productOptions)

        val product: Product = assembler.assemble(
            product = baseProduct,
            productBrand = productBrand,
            productCategory = productCategory,
            productOptions = productOptions,
            productImages = productImages,
            productInventories = productInventories,
        )

        return productStore.save(product = product).id!!
    }

    @Transactional(readOnly = true)
    override fun getProducts(
        queryFilter: ProductQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductOrderType>
    ): PaginationResponse {
        val products: List<Product> = productReader.searchProducts(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = productReader.searchProductsCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(products = products),
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    override fun getProduct(productId: Long): ProductResponse {
        val product: Product = productReader.findById(id = productId)
        return converter.toResponse(product = product)
    }

    @Transactional
    override fun updateProduct(
        productId: Long,
        request: UpdateProductRequest
    ): Long {
        val product: Product = productReader.findById(id = productId)
        updater.markAsUpdate(
            request = request,
            product = product
        )
        return productId
    }

    @Transactional
    override fun deleteProduct(productId: Long): Boolean {
        val product: Product = productReader.findById(id = productId)
        product.delete()
        return product.isDeleted
    }
}
