package com.musinsa.product.reader

import com.musinsa.product.entity.QProductCategory
import com.querydsl.core.types.dsl.BooleanExpression

data class ProductCategoryQueryFilter(
    private val parentId: Long?,
    private val name: String?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            parentId?.let { QProductCategory.productCategory._parent.id.eq(it) },
            name?.let { QProductCategory.productCategory.name.contains(it) },
            QProductCategory.productCategory.isDeleted.isFalse
        ).toTypedArray()
    }
}
