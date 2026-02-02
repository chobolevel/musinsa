package com.musinsa.snap.reader

import com.musinsa.snap.entity.QSnap
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions

class SnapQueryFilter(
    private val writerId: Long?,
    private val keyword: String?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            writerId?.let { QSnap.snap.writer.id.eq(it) },
            keyword?.let {
                Expressions.booleanTemplate(
                    "MATCH({0}) AGAINST({1} IN BOOLEAN MODE)",
                    QSnap.snap.content,
                    it
                )
            },
            QSnap.snap.isDeleted.isFalse
        ).toTypedArray()
    }
}
