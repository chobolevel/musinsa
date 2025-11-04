package com.musinsa.brand

import com.musinsa.brand.converter.BrandConverter
import com.musinsa.brand.dto.BrandResponse
import com.musinsa.brand.dto.CreateBrandRequest
import com.musinsa.brand.dto.UpdateBrandRequest
import com.musinsa.brand.entity.Brand
import com.musinsa.brand.repository.BrandQueryFilter
import com.musinsa.brand.repository.BrandRepositoryFacade
import com.musinsa.brand.service.impl.BrandServiceV1
import com.musinsa.brand.updater.BrandUpdater
import com.musinsa.brand.vo.BrandOrderType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
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
class BrandServiceV1Test {

    private val dummyBrand: Brand = DummyBrand.toEntity()

    private val dummyBrandResponse: BrandResponse = DummyBrand.toResponse()

    @Mock
    private lateinit var repository: BrandRepositoryFacade

    @Mock
    private lateinit var converter: BrandConverter

    @Mock
    private lateinit var updater: BrandUpdater

    @InjectMocks
    private lateinit var service: BrandServiceV1

    @Test
    fun createTest() {
        // given
        val request: CreateBrandRequest = DummyBrand.toCreateRequest()
        `when`(converter.toEntity(request = request)).thenReturn(dummyBrand)
        `when`(repository.save(brand = dummyBrand)).thenReturn(dummyBrand)

        // when
        val result: Long = service.createBrand(request = request)

        // then
        assertThat(result).isEqualTo(dummyBrand.id)
    }

    @Test
    fun getBrandsTest() {
        // given
        val dummyBrands: List<Brand> = listOf(dummyBrand)
        val dummyBrandResponses: List<BrandResponse> = listOf(dummyBrandResponse)
        val dummyQueryFilter = BrandQueryFilter(
            name = null,
            englishName = null,
            nation = null,
        )
        val dummyPagination = Pagination(
            page = 1,
            size = 10
        )
        val dummyOrderTypes: List<BrandOrderType> = emptyList()
        `when`(
            repository.searchBrands(
                queryFilter = dummyQueryFilter,
                pagination = dummyPagination,
                orderTypes = dummyOrderTypes
            )
        ).thenReturn(dummyBrands)
        `when`(
            repository.searchBrandsCount(
                queryFilter = dummyQueryFilter,
            )
        ).thenReturn(dummyBrands.size.toLong())
        `when`(converter.toResponseInBatch(brands = dummyBrands)).thenReturn(dummyBrandResponses)

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
    fun getBrandTest() {
        // given
        val dummyBrandId: Long = dummyBrand.id!!
        `when`(repository.findById(id = dummyBrandId)).thenReturn(dummyBrand)
        `when`(converter.toResponse(brand = dummyBrand)).thenReturn(dummyBrandResponse)

        // when
        val result: BrandResponse = service.getBrand(id = dummyBrandId)

        // then
        assertThat(result).isEqualTo(dummyBrandResponse)
    }

    @Test
    fun updateBrandTest() {
        // given
        val dummyBrandId: Long = dummyBrand.id!!
        val dummyRequest: UpdateBrandRequest = DummyBrand.toUpdateRequest()
        `when`(repository.findById(id = dummyBrandId)).thenReturn(dummyBrand)
        `when`(updater.markAsUpdate(request = dummyRequest, brand = dummyBrand)).thenReturn(dummyBrand)

        // when
        val result: Long = service.updateBrand(
            id = dummyBrandId,
            request = dummyRequest
        )

        // then
        assertThat(result).isEqualTo(dummyBrandId)
    }

    @Test
    fun deleteBrandTest() {
        // given
        val dummyBrandId: Long = dummyBrand.id!!
        `when`(repository.findById(id = dummyBrandId)).thenReturn(dummyBrand)

        // when
        val result: Boolean = service.deleteBrand(id = dummyBrandId)

        // then
        assertThat(result).isTrue()
    }
}
