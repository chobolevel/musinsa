package com.musinsa.product

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.brand.DummyProductBrand
import com.musinsa.product.category.DummyProductCategory
import com.musinsa.product.converter.ProductConverter
import com.musinsa.product.dto.CreateProductRequest
import com.musinsa.product.dto.ProductResponse
import com.musinsa.product.dto.UpdateProductRequest
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.repository.ProductBrandRepositoryFacade
import com.musinsa.product.repository.ProductCategoryRepositoryFacade
import com.musinsa.product.repository.ProductQueryFilter
import com.musinsa.product.repository.ProductRepositoryFacade
import com.musinsa.product.service.impl.ProductServiceV1
import com.musinsa.product.updater.ProductUpdater
import com.musinsa.product.vo.ProductOrderType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@DisplayName("product service v1 unit test")
@ExtendWith(MockitoExtension::class)
class ProductServiceV1Test {

    private val dummyProduct: Product = DummyProduct.toEntity()

    private val dummyProductResponse: ProductResponse = DummyProduct.toResponse()

    @Mock
    private lateinit var productRepository: ProductRepositoryFacade

    @Mock
    private lateinit var productBrandRepository: ProductBrandRepositoryFacade

    @Mock
    private lateinit var productCategoryRepository: ProductCategoryRepositoryFacade

    @Mock
    private lateinit var converter: ProductConverter

    @Mock
    private lateinit var updater: ProductUpdater

    @InjectMocks
    private lateinit var service: ProductServiceV1

    @Test
    fun createProductTest() {
        // given
        val request: CreateProductRequest = DummyProduct.toCreateRequest()
        val dummyProductBrand: ProductBrand = DummyProductBrand.toEntity()
        val dummyProductCategory: ProductCategory = DummyProductCategory.toEntity()
        `when`(converter.toEntity(request = request)).thenReturn(dummyProduct)
        `when`(productBrandRepository.findById(id = request.productBrandId)).thenReturn(dummyProductBrand)
        `when`(productCategoryRepository.findById(id = request.productCategoryId)).thenReturn(dummyProductCategory)
        `when`(productRepository.save(product = dummyProduct)).thenReturn(dummyProduct)

        // when
        val result: Long = service.createProduct(request = request)

        // then
        assertThat(result).isEqualTo(dummyProduct.id)
    }

    @Test
    fun getProductsTest() {
        // given
        val queryFilter = ProductQueryFilter(
            productBrandId = null,
            productCategoryId = null,
            name = null,
            saleStatus = null,
        )
        val pagination = Pagination(
            page = 1,
            size = 10
        )
        val orderTypes: List<ProductOrderType> = emptyList()
        val dummyProducts: List<Product> = listOf(dummyProduct)
        val dummyProductResponses: List<ProductResponse> = listOf(dummyProductResponse)
        `when`(
            productRepository.searchProducts(
                queryFilter = queryFilter,
                pagination = pagination,
                orderTypes = orderTypes
            )
        ).thenReturn(dummyProducts)
        `when`(
            productRepository.searchProductsCount(
                queryFilter = queryFilter,
            )
        ).thenReturn(dummyProducts.size.toLong())
        `when`(converter.toResponseInBatch(products = dummyProducts)).thenReturn(dummyProductResponses)

        // when
        val result: PaginationResponse = service.getProducts(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )

        // then
        assertThat(result.data).isEqualTo(dummyProductResponses)
        assertThat(result.totalCount).isEqualTo(dummyProductResponses.size.toLong())
        assertThat(result.page).isEqualTo(pagination.page)
        assertThat(result.size).isEqualTo(pagination.size)
    }

    @Test
    fun getProductTest() {
        // given
        val dummyProductId: Long = dummyProduct.id!!
        `when`(productRepository.findById(id = dummyProductId)).thenReturn(dummyProduct)
        `when`(converter.toResponse(product = dummyProduct)).thenReturn(dummyProductResponse)

        // when
        val result: ProductResponse = service.getProduct(productId = dummyProductId)

        // then
        assertThat(result).isEqualTo(dummyProductResponse)
    }

    @Test
    fun updateProductTest() {
        // given
        val dummyProductId: Long = dummyProduct.id!!
        val request: UpdateProductRequest = DummyProduct.toUpdateRequest()
        `when`(productRepository.findById(id = dummyProductId)).thenReturn(dummyProduct)
        `when`(updater.markAsUpdate(request = request, product = dummyProduct)).thenReturn(dummyProduct)

        // when
        val result: Long = service.updateProduct(productId = dummyProductId, request = request)

        // then
        assertThat(result).isEqualTo(dummyProductId)
    }
}
