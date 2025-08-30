package com.musinsa.user.entity

import com.musinsa.user.entity.QUser.user
import com.querydsl.core.types.dsl.BooleanExpression

data class UserQueryFilter(
    private val email: String?,
    private val nickname: String?,
    private val resigned: Boolean?,
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            email?.let { user.email.eq(it) },
            nickname?.let { user.nickname.eq(it) },
            resigned?.let { user.resigned.eq(it) }
        ).toTypedArray()
    }
}
