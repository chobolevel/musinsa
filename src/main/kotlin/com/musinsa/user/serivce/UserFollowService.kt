package com.musinsa.user.serivce

interface UserFollowService {

    fun following(userId: Long, followingUserId: Long): Boolean
}
