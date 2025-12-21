package com.musinsa.product

import com.musinsa.product.brand.DummyProductBrand
import com.musinsa.product.category.DummyProductCategory
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.entity.ProductOption
import com.musinsa.product.entity.QProductBrand.productBrand
import com.musinsa.product.option.DummyProductOption
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("product entity unit test")
class ProductEntityTest {

    private val dummyProduct: Product = DummyProduct.toEntity()

    private val dummyProductOption: ProductOption = DummyProductOption.toEntity()

    @Test
    fun assignProductBrandTest() {
        // given
        val dummyProductBrand: ProductBrand = DummyProductBrand.toEntity()

        // when
        dummyProduct.assignProductBrand(productBrand = dummyProductBrand)

        // then
        assertThat(dummyProduct.productBrand).isEqualTo(dummyProductBrand)
    }

    @Test
    fun assignProductCategoryTest() {
        // given
        val dummyProductCategory: ProductCategory = DummyProductCategory.toEntity()

        // when
        dummyProduct.assignProductCategory(productCategory = dummyProductCategory)

        // then
        assertThat(dummyProduct.productCategory).isEqualTo(dummyProductCategory)
    }

    @Test
    fun addProductOptionTest() {
        // given

        // when
        dummyProduct.addProductOption(productOption = dummyProductOption)

        // then
        assertThat(dummyProduct.productOptions[0]).isEqualTo(dummyProductOption)
        assertThat(dummyProductOption.product).isEqualTo(dummyProduct)
    }

    @Test
    fun deleteTest() {
        // given

        // when
        dummyProduct.delete()

        // then
        assertThat(dummyProduct.isDeleted).isTrue
    }
}
