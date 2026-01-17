package com.musinsa.product.image

import com.musinsa.product.DummyProduct
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductImage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("product image entity unit test")
class ProductImageEntityTest {

    private val dummyProductImage: ProductImage = DummyProductImage.toEntity()

    @Test
    fun assignProductTest() {
        // given
        val dummyProduct: Product = DummyProduct.toEntity()

        // when
        dummyProductImage.assignProduct(product = dummyProduct)

        // then
        assertThat(dummyProductImage.product).isEqualTo(dummyProduct)
    }

    @Test
    fun deleteTest() {
        // given & when
        dummyProductImage.delete()

        // then
        assertThat(dummyProductImage.isDeleted).isTrue()
    }
}
