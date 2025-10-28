package com.musinsa.user.entity

import org.springframework.data.jpa.repository.JpaRepository

interface UserFollowRepository : JpaRepository<UserFollow, Long> {

    fun deleteByFollowerIdAndFollowingId(followerId: Long, followingId: Long)
}
