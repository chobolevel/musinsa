package com.musinsa.snap.repository

import com.musinsa.snap.entity.QSnap.snap
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions

class SnapQueryFilter(
    private val writerId: Long?,
    private val keyword: String?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            writerId?.let { snap.writer.id.eq(it) },
            keyword?.let {
                Expressions.booleanTemplate(
                    "MATCH({0}) AGAINST({1} IN BOOLEAN MODE)",
                    snap.content,
                    it
                )
            },
            snap.isDeleted.isFalse
        ).toTypedArray()
    }
}
