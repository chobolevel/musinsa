package com.musinsa.product.category

import com.musinsa.product.entity.ProductCategory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("product category entity test")
class ProductCategoryEntityTest {

    private val dummyProductCategory: ProductCategory = DummyProductCategory.toEntity()

    @Test
    fun assignParentTest() {
        // given
        val dummyParentProductCategory: ProductCategory = DummyProductCategory.toParentEntity()

        // when
        dummyProductCategory.assignParent(parent = dummyParentProductCategory)

        // then
        assertThat(dummyProductCategory.parent).isEqualTo(dummyParentProductCategory)
    }

    @Test
    fun deleteTest() {
        // given

        // when
        dummyProductCategory.delete()

        // then
        assertThat(dummyProductCategory.isDeleted).isTrue()
    }
}
