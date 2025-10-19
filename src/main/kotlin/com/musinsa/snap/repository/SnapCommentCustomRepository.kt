package com.musinsa.snap.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.snap.entity.QSnapComment.snapComment
import com.musinsa.snap.entity.SnapComment
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class SnapCommentCustomRepository : QuerydslRepositorySupport(SnapComment::class.java) {

    fun searchSnapComments(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<SnapComment> {
        return from(snapComment)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchSnapCommentsCount(
        booleanExpressions: Array<BooleanExpression>,
    ): Long {
        return from(snapComment)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
