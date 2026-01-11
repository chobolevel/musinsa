package com.musinsa.user.serivce.impl

import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import com.musinsa.user.entity.follow.UserFollow
import com.musinsa.user.entity.follow.UserFollowRepositoryFacade
import com.musinsa.user.serivce.UserFollowService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserFollowServiceV1(
    private val repository: UserFollowRepositoryFacade,
    private val userRepository: UserRepositoryFacade
) : UserFollowService {

    @Transactional
    override fun following(userId: Long, followingUserId: Long): Boolean {
        val user: User = userRepository.findById(id = userId)
        val followingUser: User = userRepository.findById(id = followingUserId)
        val userFollow = UserFollow().also { userFollow ->
            userFollow.assignFollower(user = user)
            userFollow.assignFollowing(user = followingUser)
        }
        repository.save(userFollow = userFollow)
        return true
    }

    @Transactional
    override fun unFollowing(userId: Long, unFollowingUserId: Long): Boolean {
        repository.deleteByFollowerIdAndFollowingId(
            followerId = userId,
            followingId = unFollowingUserId
        )
        return true
    }
}
