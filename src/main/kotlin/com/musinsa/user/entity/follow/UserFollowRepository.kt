package com.musinsa.user.entity.follow

import org.springframework.data.jpa.repository.JpaRepository

interface UserFollowRepository : JpaRepository<UserFollow, Long> {

    fun deleteByFollowerIdAndFollowingId(followerId: Long, followingId: Long)
}
