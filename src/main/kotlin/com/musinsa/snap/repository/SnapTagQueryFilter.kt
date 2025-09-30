package com.musinsa.snap.repository

import com.musinsa.snap.entity.QSnapTag.snapTag
import com.musinsa.snap.vo.SnapTagType
import com.querydsl.core.types.dsl.BooleanExpression

class SnapTagQueryFilter(
    private val type: SnapTagType?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            type?.let { snapTag.type.eq(it) },
            snapTag.isDeleted.isFalse
        ).toTypedArray()
    }
}
