package com.musinsa.product.repository

import com.musinsa.product.entity.QProductCategory.productCategory
import com.querydsl.core.types.dsl.BooleanExpression

data class ProductCategoryQueryFilter(
    private val parentId: Long?,
    private val name: String?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            parentId?.let { productCategory.parent.id.eq(it) },
            name?.let { productCategory.name.contains(it) },
            productCategory.isDeleted.isFalse
        ).toTypedArray()
    }
}
