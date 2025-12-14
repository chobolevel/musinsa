package com.musinsa.product

import com.musinsa.product.brand.DummyProductBrand
import com.musinsa.product.category.DummyProductCategory
import com.musinsa.product.converter.ProductConverter
import com.musinsa.product.dto.CreateProductRequest
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.repository.ProductBrandRepositoryFacade
import com.musinsa.product.repository.ProductCategoryRepositoryFacade
import com.musinsa.product.service.impl.ProductServiceV1
import com.musinsa.snap.repository.ProductRepositoryFacade
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@DisplayName("product service v1 unit test")
@ExtendWith(MockitoExtension::class)
class ProductServiceV1Test {

    private val dummyProduct: Product = DummyProduct.toEntity()

    @Mock
    private lateinit var productRepository: ProductRepositoryFacade

    @Mock
    private lateinit var productBrandRepository: ProductBrandRepositoryFacade

    @Mock
    private lateinit var productCategoryRepository: ProductCategoryRepositoryFacade

    @Mock
    private lateinit var converter: ProductConverter

    @InjectMocks
    private lateinit var service: ProductServiceV1

    @Test
    fun createProductTest() {
        // given
        val request: CreateProductRequest = DummyProduct.toCreateRequest()
        val dummyProductBrand: ProductBrand = DummyProductBrand.toEntity()
        val dummyProductCategory: ProductCategory = DummyProductCategory.toEntity()
        Mockito.`when`(converter.toEntity(request = request)).thenReturn(dummyProduct)
        Mockito.`when`(productBrandRepository.findById(id = request.productBrandId)).thenReturn(dummyProductBrand)
        Mockito.`when`(productCategoryRepository.findById(id = request.productCategoryId)).thenReturn(dummyProductCategory)
        Mockito.`when`(productRepository.save(product = dummyProduct)).thenReturn(dummyProduct)

        // when
        val result: Long = service.createProduct(request = request)

        // then
        Assertions.assertThat(result).isEqualTo(dummyProduct.id)
    }
}
