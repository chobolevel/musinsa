package com.musinsa.snap.repository

import com.musinsa.snap.entity.QSnapLike.snapLike
import com.querydsl.core.types.dsl.BooleanExpression

class SnapLikeQueryFilter(
    private val snapId: Long?,
    private val userId: Long?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            snapId?.let { snapLike.snap.id.eq(it) },
            userId?.let { snapLike.user.id.eq(it) }
        ).toTypedArray()
    }
}
