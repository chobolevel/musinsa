package com.musinsa.user.reader

import com.musinsa.user.entity.QUserFollow.userFollow
import com.querydsl.core.types.dsl.BooleanExpression

class UserFollowQueryFilter(
    private val followerId: Long?,
    private val followingId: Long?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            followerId?.let { userFollow._follower.id.eq(it) },
            followingId?.let { userFollow._following.id.eq(it) }
        ).toTypedArray()
    }
}
