package com.musinsa.product.reader

import com.musinsa.common.vo.NationType
import com.musinsa.product.entity.QProductBrand
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
                    QProductBrand.productBrand.name,
                    it
                )
            },
            englishName?.let {
                Expressions.booleanTemplate(
                    "MATCH({0}) AGAINST({1} IN BOOLEAN MODE)",
                    QProductBrand.productBrand.englishName,
                    it
                )
            },
            nation?.let { QProductBrand.productBrand.nation.eq(it) }
        ).toTypedArray()
    }
}
