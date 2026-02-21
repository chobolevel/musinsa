package com.musinsa.product.reader

import com.musinsa.product.entity.QProduct
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
            productBrandId?.let { QProduct.product.productBrand.id.eq(it) },
            productCategoryId?.let { QProduct.product.productCategory.id.eq(it) },
            name?.let {
                Expressions.booleanTemplate(
                    "MATCH({0}) AGAINST({1} IN BOOLEAN MODE)",
                    QProduct.product.name,
                    it
                )
            },
            saleStatus?.let { QProduct.product.saleStatus.eq(it) },
            QProduct.product.isDeleted.isFalse
        ).toTypedArray()
    }
}
