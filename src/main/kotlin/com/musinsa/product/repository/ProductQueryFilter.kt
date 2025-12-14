package com.musinsa.product.repository

import com.musinsa.product.entity.QProduct.product
import com.musinsa.product.vo.ProductSaleStatus
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions

data class ProductQueryFilter(
    private val productBrandId: Long?,
    private val productCategoryId: Long?,
    private val name: String?,
    private val saleStatus: ProductSaleStatus?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            productBrandId?.let { product.productBrand.id.eq(it) },
            productCategoryId?.let { product.productCategory.id.eq(it) },
            name?.let {
                Expressions.booleanTemplate(
                    "MATCH({0}) AGAINST({1} IN BOOLEAN MODE)",
                    product.name,
                    it
                )
            },
            saleStatus?.let { product.saleStatus.eq(it) },
            product.isDeleted.isFalse
        ).toTypedArray()
    }
}
