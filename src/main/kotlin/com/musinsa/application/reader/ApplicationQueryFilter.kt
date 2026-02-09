package com.musinsa.application.reader

import com.musinsa.application.entity.QApplication
import com.querydsl.core.types.dsl.BooleanExpression

data class ApplicationQueryFilter(
    private val name: String?
) {
    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            name?.let { QApplication.application.name.contains(it) },
            QApplication.application.isDeleted.isFalse
        ).toTypedArray()
    }
}
