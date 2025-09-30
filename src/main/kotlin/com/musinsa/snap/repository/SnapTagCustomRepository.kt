package com.musinsa.snap.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.snap.entity.QSnapTag.snapTag
import com.musinsa.snap.entity.SnapTag
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class SnapTagCustomRepository : QuerydslRepositorySupport(SnapTagRepository::class.java) {

    fun searchSnapTags(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<SnapTag> {
        return from(snapTag)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchSnapTagsCount(
        booleanExpressions: Array<BooleanExpression>,
    ): Long {
        return from(snapTag)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
