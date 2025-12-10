package com.musinsa.product.category

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.converter.ProductCategoryConverter
import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.dto.ProductCategoryResponse
import com.musinsa.product.dto.UpdateProductCategoryRequest
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.repository.ProductCategoryQueryFilter
import com.musinsa.product.repository.ProductCategoryRepositoryFacade
import com.musinsa.product.service.impl.ProductCategoryServiceV1
import com.musinsa.product.updater.ProductCategoryUpdater
import com.musinsa.product.vo.ProductCategoryOrderType
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

    private val dummyProductCategoryResponse: ProductCategoryResponse = DummyProductCategory.toResponse()

    @Mock
    private lateinit var repository: ProductCategoryRepositoryFacade

    @Mock
    private lateinit var converter: ProductCategoryConverter

    @Mock
    private lateinit var updater: ProductCategoryUpdater

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

    @Test
    fun getProductCategoriesTest() {
        // given
        val queryFilter = ProductCategoryQueryFilter(
            parentId = null,
            name = null
        )
        val pagination = Pagination(
            page = 1,
            size = 20
        )
        val orderTypes: List<ProductCategoryOrderType> = emptyList()
        val dummyProductCategories: List<ProductCategory> = listOf(dummyProductCategory)
        val dummyProductCategoryResponses: List<ProductCategoryResponse> = listOf(dummyProductCategoryResponse)
        `when`(
            repository.searchProductCategories(
                queryFilter = queryFilter,
                pagination = pagination,
                orderTypes = orderTypes
            )
        ).thenReturn(dummyProductCategories)
        `when`(
            repository.searchProductCategoriesCount(
                queryFilter = queryFilter,
            )
        ).thenReturn(dummyProductCategories.size.toLong())
        `when`(converter.toResponses(productCategories = dummyProductCategories)).thenReturn(dummyProductCategoryResponses)

        // when
        val result: PaginationResponse = service.getProductCategories(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )

        // then
        assertThat(result.page).isEqualTo(pagination.page)
        assertThat(result.size).isEqualTo(pagination.size)
        assertThat(result.data).isEqualTo(dummyProductCategoryResponses)
        assertThat(result.totalCount).isEqualTo(dummyProductCategoryResponses.size.toLong())
    }

    @Test
    fun getProductCategoryTest() {
        // given
        val dummyProductCategoryId: Long = dummyProductCategory.id!!
        `when`(repository.findById(id = dummyProductCategoryId)).thenReturn(dummyProductCategory)
        `when`(converter.toResponse(productCategory = dummyProductCategory)).thenReturn(dummyProductCategoryResponse)

        // when
        val result: ProductCategoryResponse = service.getProductCategory(productCategoryId = dummyProductCategoryId)

        // then
        assertThat(result.id).isEqualTo(dummyProductCategoryId)
    }

    @Test
    fun updateProductCategoryTest() {
        // given
        val dummyProductCategoryId: Long = dummyProductCategory.id!!
        val request: UpdateProductCategoryRequest = DummyProductCategory.toUpdateRequest()
        `when`(repository.findById(id = dummyProductCategoryId)).thenReturn(dummyProductCategory)
        `when`(updater.markAsUpdate(productCategory = dummyProductCategory, request = request)).thenReturn(dummyProductCategory)

        // when
        val result: Long = service.updateProductCategory(
            productCategoryId = dummyProductCategoryId,
            request = request
        )

        // then
        assertThat(result).isEqualTo(dummyProductCategoryId)
    }

    @Test
    fun deleteProductCategoryTest() {
        // given
        val dummyProductCategoryId: Long = dummyProductCategory.id!!
        `when`(repository.findById(id = dummyProductCategoryId)).thenReturn(dummyProductCategory)

        // when
        val result: Boolean = service.deleteProductCategory(productCategoryId = dummyProductCategoryId)

        // then
        assertThat(result).isTrue
    }
}
