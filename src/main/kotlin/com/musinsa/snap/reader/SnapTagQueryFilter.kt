package com.musinsa.snap.reader

import com.musinsa.snap.entity.QSnapTag
import com.musinsa.snap.vo.SnapTagType
import com.querydsl.core.types.dsl.BooleanExpression

class SnapTagQueryFilter(
    private val type: SnapTagType?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            type?.let { QSnapTag.snapTag.type.eq(it) },
            QSnapTag.snapTag.isDeleted.isFalse
        ).toTypedArray()
    }
}
