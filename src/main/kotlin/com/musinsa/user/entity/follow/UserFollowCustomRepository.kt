package com.musinsa.user.entity.follow

import com.musinsa.common.dto.Pagination
import com.musinsa.user.entity.follow.QUserFollow.userFollow
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class UserFollowCustomRepository : QuerydslRepositorySupport(UserFollow::class.java) {

    fun searchUserFollows(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<UserFollow> {
        return from(userFollow)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchUserFollowsCount(
        booleanExpressions: Array<BooleanExpression>,
    ): Long {
        return from(userFollow)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
