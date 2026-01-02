package com.musinsa.product.option

import com.musinsa.product.DummyProduct
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductOption
import com.musinsa.product.entity.ProductOptionValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("product option entity unit test")
class ProductOptionEntityTest {

    private val dummyProductOption: ProductOption = DummyProductOption.toEntity()

    @Test
    fun assignProductTest() {
        // given
        val dummyProduct: Product = DummyProduct.toEntity()

        // when
        dummyProductOption.assignProduct(product = dummyProduct)

        // then
        assertThat(dummyProductOption.product).isEqualTo(dummyProduct)
    }

    @Test
    fun deleteTest() {
        // given

        // when
        dummyProductOption.delete()

        // then
        assertThat(dummyProductOption.isDeleted).isTrue
    }

    @Test
    fun addProductOptionValuesTest() {
        // given
        val dummyProductOptionValue: ProductOptionValue = DummyProductOptionValue.toEntity()

        // when
        dummyProductOption.addProductOptionValue(productOptionValue = dummyProductOptionValue)

        // then
        assertThat(dummyProductOption.productOptionValues[0]).isEqualTo(dummyProductOptionValue)
        assertThat(dummyProductOption.productOptionValues[0].productOption).isEqualTo(dummyProductOption)
    }
}
