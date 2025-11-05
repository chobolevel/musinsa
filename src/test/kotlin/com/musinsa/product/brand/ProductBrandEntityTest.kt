package com.musinsa.product.brand

import com.musinsa.product.entity.ProductBrand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("brand entity unit test")
class ProductBrandEntityTest {

    private val dummyProductBrand: ProductBrand = DummyProductBrand.toEntity()

    @Test
    fun deleteTest() {
        // given

        // when
        dummyProductBrand.delete()

        // then
        assertThat(dummyProductBrand.isDeleted).isTrue()
    }
}
