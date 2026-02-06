package com.musinsa.user.serivce.impl

import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserFollow
import com.musinsa.user.reader.UserReader
import com.musinsa.user.serivce.UserFollowService
import com.musinsa.user.store.UserFollowStore
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserFollowServiceV1(
    private val userReader: UserReader,
    private val userFollowStore: UserFollowStore,
) : UserFollowService {

    @Transactional
    override fun following(userId: Long, followingUserId: Long): Boolean {
        val user: User = userReader.findById(id = userId)
        val followingUser: User = userReader.findById(id = followingUserId)
        val userFollow = UserFollow().also { userFollow ->
            userFollow.assignFollower(user = user)
            userFollow.assignFollowing(user = followingUser)
        }
        userFollowStore.save(userFollow = userFollow)
        return true
    }

    @Transactional
    override fun unFollowing(userId: Long, unFollowingUserId: Long): Boolean {
        userFollowStore.deleteByFollowerIdAndFollowingId(
            followerId = userId,
            followingId = unFollowingUserId
        )
        return true
    }
}
