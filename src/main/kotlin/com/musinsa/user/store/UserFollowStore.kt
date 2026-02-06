package com.musinsa.user.store

import com.musinsa.user.entity.UserFollow
import com.musinsa.user.repository.UserFollowRepository
import org.springframework.stereotype.Component

@Component
class UserFollowStore(
    private val userFollowRepository: UserFollowRepository
) {

    fun save(userFollow: UserFollow): UserFollow {
        return userFollowRepository.save(userFollow)
    }

    fun deleteByFollowerIdAndFollowingId(followerId: Long, followingId: Long) {
        return userFollowRepository.deleteByFollowerIdAndFollowingId(followerId, followingId)
    }
}
