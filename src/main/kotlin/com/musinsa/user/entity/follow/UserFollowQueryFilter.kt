package com.musinsa.user.entity.follow

import com.musinsa.user.entity.follow.QUserFollow.userFollow
import com.querydsl.core.types.dsl.BooleanExpression

class UserFollowQueryFilter(
    private val followerId: Long?,
    private val followingId: Long?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            followerId?.let { userFollow.follower.id.eq(it) },
            followingId?.let { userFollow.following.id.eq(it) }
        ).toTypedArray()
    }
}
