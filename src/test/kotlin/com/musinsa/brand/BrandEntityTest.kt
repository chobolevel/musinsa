package com.musinsa.brand

import com.musinsa.brand.entity.Brand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("brand entity unit test")
class BrandEntityTest {

    private val dummyBrand: Brand = DummyBrand.toEntity()

    @Test
    fun deleteTest() {
        // given

        // when
        dummyBrand.delete()

        // then
        assertThat(dummyBrand.isDeleted).isTrue()
    }
}
