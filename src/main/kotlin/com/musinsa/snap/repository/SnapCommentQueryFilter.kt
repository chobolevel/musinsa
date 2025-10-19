package com.musinsa.snap.repository

import com.musinsa.snap.entity.QSnapComment.snapComment
import com.querydsl.core.types.dsl.BooleanExpression

class SnapCommentQueryFilter(
    private val snapId: Long?,
    private val userId: Long?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            snapId?.let { snapComment.snap.id.eq(it) },
            userId?.let { snapComment.user.id.eq(it) },
            snapComment.isDeleted.isFalse
        ).toTypedArray()
    }
}
