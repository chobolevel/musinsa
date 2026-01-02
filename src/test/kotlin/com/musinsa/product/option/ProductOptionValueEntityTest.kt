package com.musinsa.product.option

import com.musinsa.product.entity.ProductOption
import com.musinsa.product.entity.ProductOptionValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("product option value entity unit test")
class ProductOptionValueEntityTest {

    private val dummyProductOptionValue: ProductOptionValue = DummyProductOptionValue.toEntity()

    @Test
    fun assignProductOptionTest() {
        // given
        val dummyProductOption: ProductOption = DummyProductOption.toEntity()

        // when
        dummyProductOptionValue.assignProductOption(productOption = dummyProductOption)

        // then
        assertThat(dummyProductOptionValue.productOption).isEqualTo(dummyProductOption)
    }

    @Test
    fun deleteTest() {
        // given & when
        dummyProductOptionValue.delete()

        // then
        assertThat(dummyProductOptionValue.isDeleted).isTrue()
    }
}
