package com.musinsa.product.inventory

import com.musinsa.product.DummyProduct
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductInventory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("product inventory unit test")
class ProductInventoryEntityTest {

    private val dummyProductInventory: ProductInventory = DummyProductInventory.toEntity()

    @Test
    fun assignProductTest() {
        // given
        val dummyProduct: Product = DummyProduct.toEntity()

        // when
        dummyProductInventory.assignProduct(product = dummyProduct)

        // then
        assertThat(dummyProductInventory.product).isEqualTo(dummyProduct)
    }
}
