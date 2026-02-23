package com.musinsa.user

import com.musinsa.user.dto.UserFollowResponse
import com.musinsa.user.entity.UserFollow

object DummyUserFollow {
    private val id: Long = 1L
    private const val createdAt: Long = 0L
    private const val updatedAt: Long = 0L

    private val dummyUserFollow: UserFollow by lazy {
        UserFollow().also {
            it.id = id
        }
    }

    private val dummyUserFollowResponse: UserFollowResponse by lazy {
        UserFollowResponse(
            follower = DummyUser.toResponse(),
            following = DummyUser.toSocialUserResponse(),
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    fun toEntity(): UserFollow = dummyUserFollow

    fun toResponse(): UserFollowResponse = dummyUserFollowResponse
}
