package com.musinsa.user.assembler

import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserFollow
import org.springframework.stereotype.Component

@Component
class UserFollowAssembler {

    fun assemble(
        followerUser: User,
        followingUser: User
    ): UserFollow {
        val userFollow = UserFollow()
        userFollow.assignFollower(user = followerUser)
        userFollow.assignFollowing(user = followingUser)
        return userFollow
    }
}
