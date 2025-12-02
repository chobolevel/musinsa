package com.musinsa.product.category

import com.musinsa.product.converter.ProductCategoryConverter
import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.repository.ProductCategoryRepositoryFacade
import com.musinsa.product.service.impl.ProductCategoryServiceV1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@DisplayName("product category v1 service unit test")
@ExtendWith(MockitoExtension::class)
class ProductCategoryServiceV1Test {

    private val dummyProductCategory: ProductCategory = DummyProductCategory.toEntity()

    @Mock
    private lateinit var repository: ProductCategoryRepositoryFacade

    @Mock
    private lateinit var converter: ProductCategoryConverter

    @InjectMocks
    private lateinit var service: ProductCategoryServiceV1

    @Test
    fun createProductCategoryTest() {
        // given
        val dummyRequest: CreateProductCategoryRequest = DummyProductCategory.toCreateRequest()
        `when`(converter.toEntity(request = dummyRequest)).thenReturn(dummyProductCategory)
        `when`(repository.save(productCategory = dummyProductCategory)).thenReturn(dummyProductCategory)

        // when
        val result: Long = service.createProductCategory(request = dummyRequest)

        // then
        assertThat(result).isEqualTo(dummyProductCategory.id)
    }
}
