package com.musinsa.snap.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.snap.entity.QSnap.snap
import com.musinsa.snap.entity.Snap
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class SnapCustomRepository : QuerydslRepositorySupport(Snap::class.java) {

    fun searchSnaps(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<Snap> {
        return from(snap)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchSnapsCount(
        booleanExpressions: Array<BooleanExpression>
    ): Long {
        return from(snap)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
