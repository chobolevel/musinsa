package com.musinsa.product.repository

import com.musinsa.common.vo.NationType
import com.musinsa.product.entity.QProductBrand.productBrand
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions

class ProductBrandQueryFilter(
    private val name: String?,
    private val englishName: String?,
    private val nation: NationType?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            name?.let {
                Expressions.booleanTemplate(
                    "MATCH({0}) AGAINST({1} IN BOOLEAN MODE)",
                    productBrand.name,
                    it
                )
            },
            englishName?.let {
                Expressions.booleanTemplate(
                    "MATCH({0}) AGAINST({1} IN BOOLEAN MODE)",
                    productBrand.englishName,
                    it
                )
            },
            nation?.let { productBrand.nation.eq(it) }
        ).toTypedArray()
    }
}
