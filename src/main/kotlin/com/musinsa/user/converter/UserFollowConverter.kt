package com.musinsa.user.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.user.dto.UserFollowResponse
import com.musinsa.user.entity.UserFollow
import org.springframework.stereotype.Component

@Component
class UserFollowConverter(
    private val userConverter: UserConverter
) {

    fun toResponse(userFollow: UserFollow): UserFollowResponse {
        return UserFollowResponse(
            follower = userConverter.toResponse(user = userFollow.follower!!),
            following = userConverter.toResponse(user = userFollow.following!!),
            createdAt = userFollow.createdAt!!.toMillis(),
            updatedAt = userFollow.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(userFollows: List<UserFollow>): List<UserFollowResponse> {
        return userFollows.map { toResponse(it) }
    }
}
