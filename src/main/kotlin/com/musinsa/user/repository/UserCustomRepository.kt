package com.musinsa.user.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.user.entity.QUser
import com.musinsa.user.entity.User
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class UserCustomRepository : QuerydslRepositorySupport(User::class.java) {

    fun searchUsers(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<User> {
        return from(QUser.user)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchUsersCount(
        booleanExpressions: Array<BooleanExpression>,
    ): Long {
        return from(QUser.user)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
