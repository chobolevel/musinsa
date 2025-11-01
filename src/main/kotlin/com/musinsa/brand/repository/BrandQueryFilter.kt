package com.musinsa.brand.repository

import com.musinsa.brand.entity.QBrand.brand
import com.musinsa.common.vo.NationType
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions

class BrandQueryFilter(
    private val name: String?,
    private val englishName: String?,
    private val nation: NationType?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            name?.let {
                Expressions.booleanTemplate(
                    "MATCH({0}) AGAINST({1} IN BOOLEAN MODE)",
                    brand.name,
                    it
                )
            },
            englishName?.let {
                Expressions.booleanTemplate(
                    "MATCH({0}) AGAINST({1} IN BOOLEAN MODE)",
                    brand.englishName,
                    it
                )
            },
            nation?.let { brand.nation.eq(it) }
        ).toTypedArray()
    }
}
