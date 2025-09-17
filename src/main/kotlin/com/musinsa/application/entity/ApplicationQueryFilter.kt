package com.musinsa.application.entity

import com.musinsa.application.entity.QApplication.application
import com.querydsl.core.types.dsl.BooleanExpression

data class ApplicationQueryFilter(
    private val name: String?
) {
    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            name?.let { application.name.contains(it) },
            application.isDeleted.isFalse
        ).toTypedArray()
    }
}
