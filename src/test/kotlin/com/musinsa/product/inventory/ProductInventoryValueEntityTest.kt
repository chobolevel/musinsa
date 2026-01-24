package com.musinsa.product.inventory

import com.musinsa.product.entity.ProductInventory
import com.musinsa.product.entity.ProductInventoryValue
import com.musinsa.product.entity.ProductOptionValue
import com.musinsa.product.option.DummyProductOptionValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("product inventory value entity unit test")
class ProductInventoryValueEntityTest {

    private val dummyProductInventoryValue: ProductInventoryValue = DummyProductInventoryValue.toEntity()

    @Test
    fun assignProductInventoryTest() {
        // given
        val dummyProductInventory: ProductInventory = DummyProductInventory.toEntity()

        // when
        dummyProductInventoryValue.assignProductInventory(productInventory = dummyProductInventory)

        // then
        assertThat(dummyProductInventoryValue.productInventory).isEqualTo(dummyProductInventory)
    }

    @Test
    fun assignProductOptionValueTest() {
        // given
        val dummyProductOptionValue: ProductOptionValue = DummyProductOptionValue.toEntity()

        // when
        dummyProductInventoryValue.assignProductOptionValue(productOptionValue = dummyProductOptionValue)

        // then
        assertThat(dummyProductInventoryValue.productOptionValue).isEqualTo(dummyProductOptionValue)
    }
}
