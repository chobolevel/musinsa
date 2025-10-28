package com.musinsa.user.serivce

interface UserFollowService {

    fun following(userId: Long, followingUserId: Long): Boolean

    fun unFollowing(userId: Long, unFollowingUserId: Long): Boolean
}
