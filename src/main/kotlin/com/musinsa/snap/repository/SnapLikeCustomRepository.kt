package com.musinsa.snap.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.snap.entity.QSnapLike.snapLike
import com.musinsa.snap.entity.SnapLike
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class SnapLikeCustomRepository : QuerydslRepositorySupport(SnapLike::class.java) {

    fun searchSnapLikes(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<SnapLike> {
        return from(snapLike)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchSnapLikesCount(
        booleanExpressions: Array<BooleanExpression>
    ): Long {
        return from(snapLike)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
