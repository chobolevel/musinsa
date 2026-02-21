package com.musinsa.snap.reader

import com.musinsa.snap.entity.QSnapComment
import com.querydsl.core.types.dsl.BooleanExpression

class SnapCommentQueryFilter(
    private val snapId: Long?,
    private val userId: Long?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            snapId?.let { QSnapComment.snapComment.snap.id.eq(it) },
            userId?.let { QSnapComment.snapComment.writer.id.eq(it) },
            QSnapComment.snapComment.isDeleted.isFalse
        ).toTypedArray()
    }
}
