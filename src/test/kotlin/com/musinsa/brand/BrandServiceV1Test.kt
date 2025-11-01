package com.musinsa.brand

import com.musinsa.brand.converter.BrandConverter
import com.musinsa.brand.dto.CreateBrandRequest
import com.musinsa.brand.entity.Brand
import com.musinsa.brand.repository.BrandRepositoryFacade
import com.musinsa.brand.service.impl.BrandServiceV1
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

    @Mock
    private lateinit var repository: BrandRepositoryFacade

    @Mock
    private lateinit var converter: BrandConverter

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
}
