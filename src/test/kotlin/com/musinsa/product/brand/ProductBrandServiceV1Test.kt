package com.musinsa.product.brand

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.converter.ProductBrandConverter
import com.musinsa.product.dto.CreateProductBrandRequest
import com.musinsa.product.dto.ProductBrandResponse
import com.musinsa.product.dto.UpdateProductBrandRequest
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.repository.ProductBrandQueryFilter
import com.musinsa.product.repository.ProductBrandRepositoryFacade
import com.musinsa.product.service.impl.ProductBrandServiceV1
import com.musinsa.product.updater.ProductBrandUpdater
import com.musinsa.product.vo.ProductBrandOrderType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@DisplayName("brand service v1 unit test")
@ExtendWith(MockitoExtension::class)
class ProductBrandServiceV1Test {

    private val dummyProductBrand: ProductBrand = DummyProductBrand.toEntity()

    private val dummyBrandResponse: ProductBrandResponse = DummyProductBrand.toResponse()

    @Mock
    private lateinit var repository: ProductBrandRepositoryFacade

    @Mock
    private lateinit var converter: ProductBrandConverter

    @Mock
    private lateinit var updater: ProductBrandUpdater

    @InjectMocks
    private lateinit var service: ProductBrandServiceV1

    @Test
    fun createProductBrandTest() {
        // given
        val request: CreateProductBrandRequest = DummyProductBrand.toCreateRequest()
        `when`(converter.toEntity(request = request)).thenReturn(dummyProductBrand)
        `when`(repository.save(productBrand = dummyProductBrand)).thenReturn(dummyProductBrand)

        // when
        val result: Long = service.createBrand(request = request)

        // then
        assertThat(result).isEqualTo(dummyProductBrand.id)
    }

    @Test
    fun getProductBrandsTest() {
        // given
        val dummyProductBrands: List<ProductBrand> = listOf(dummyProductBrand)
        val dummyBrandResponses: List<ProductBrandResponse> = listOf(dummyBrandResponse)
        val dummyQueryFilter = ProductBrandQueryFilter(
            name = null,
            englishName = null,
            nation = null,
        )
        val dummyPagination = Pagination(
            page = 1,
            size = 10
        )
        val dummyOrderTypes: List<ProductBrandOrderType> = emptyList()
        `when`(
            repository.searchProductBrands(
                queryFilter = dummyQueryFilter,
                pagination = dummyPagination,
                orderTypes = dummyOrderTypes
            )
        ).thenReturn(dummyProductBrands)
        `when`(
            repository.searchProductBrandsCount(
                queryFilter = dummyQueryFilter,
            )
        ).thenReturn(dummyProductBrands.size.toLong())
        `when`(converter.toResponseInBatch(productBrands = dummyProductBrands)).thenReturn(dummyBrandResponses)

        // when
        val result: PaginationResponse = service.getBrands(
            queryFilter = dummyQueryFilter,
            pagination = dummyPagination,
            orderTypes = dummyOrderTypes
        )

        // then
        assertThat(result.page).isEqualTo(dummyPagination.page)
        assertThat(result.size).isEqualTo(dummyPagination.size)
        assertThat(result.data).isEqualTo(dummyBrandResponses)
        assertThat(result.totalCount).isEqualTo(dummyBrandResponses.size.toLong())
    }

    @Test
    fun getProductBrandTest() {
        // given
        val dummyBrandId: Long = dummyProductBrand.id!!
        `when`(repository.findById(id = dummyBrandId)).thenReturn(dummyProductBrand)
        `when`(converter.toResponse(productBrand = dummyProductBrand)).thenReturn(dummyBrandResponse)

        // when
        val result: ProductBrandResponse = service.getBrand(id = dummyBrandId)

        // then
        assertThat(result).isEqualTo(dummyBrandResponse)
    }

    @Test
    fun updateProductBrandTest() {
        // given
        val dummyBrandId: Long = dummyProductBrand.id!!
        val dummyRequest: UpdateProductBrandRequest = DummyProductBrand.toUpdateRequest()
        `when`(repository.findById(id = dummyBrandId)).thenReturn(dummyProductBrand)
        `when`(updater.markAsUpdate(request = dummyRequest, productBrand = dummyProductBrand)).thenReturn(dummyProductBrand)

        // when
        val result: Long = service.updateBrand(
            id = dummyBrandId,
            request = dummyRequest
        )

        // then
        assertThat(result).isEqualTo(dummyBrandId)
    }

    @Test
    fun deleteProductBrandTest() {
        // given
        val dummyBrandId: Long = dummyProductBrand.id!!
        `when`(repository.findById(id = dummyBrandId)).thenReturn(dummyProductBrand)

        // when
        val result: Boolean = service.deleteBrand(id = dummyBrandId)

        // then
        assertThat(result).isTrue()
    }
}
