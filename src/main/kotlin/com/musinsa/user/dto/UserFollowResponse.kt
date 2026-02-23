package com.musinsa.user.dto

data class UserFollowResponse(
    val follower: UserResponse,
    val following: UserResponse,
    val createdAt: Long,
    val updatedAt: Long
)
