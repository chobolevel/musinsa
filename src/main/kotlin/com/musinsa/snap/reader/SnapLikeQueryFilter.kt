package com.musinsa.snap.reader

import com.musinsa.snap.entity.QSnapLike
import com.querydsl.core.types.dsl.BooleanExpression

class SnapLikeQueryFilter(
    private val snapId: Long?,
    private val userId: Long?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            snapId?.let { QSnapLike.snapLike.snap.id.eq(it) },
            userId?.let { QSnapLike.snapLike.user.id.eq(it) }
        ).toTypedArray()
    }
}
