package com.musinsa.snap.repository

import com.musinsa.snap.entity.QSnap.snap
import com.querydsl.core.types.dsl.BooleanExpression

class SnapQueryFilter(
    private val writerId: Long?,
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            writerId?.let { snap.writer.id.eq(it) },
            snap.isDeleted.isFalse
        ).toTypedArray()
    }
}
