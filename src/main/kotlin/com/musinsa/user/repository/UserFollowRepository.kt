package com.musinsa.user.repository

import com.musinsa.user.entity.UserFollow
import org.springframework.data.jpa.repository.JpaRepository

interface UserFollowRepository : JpaRepository<UserFollow, Long> {

    fun deleteByFollowerIdAndFollowingId(followerId: Long, followingId: Long)
}
