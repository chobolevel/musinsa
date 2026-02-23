package com.musinsa.user.serivce.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.user.assembler.UserFollowAssembler
import com.musinsa.user.converter.UserFollowConverter
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserFollow
import com.musinsa.user.reader.UserFollowQueryFilter
import com.musinsa.user.reader.UserFollowReader
import com.musinsa.user.reader.UserReader
import com.musinsa.user.serivce.UserFollowService
import com.musinsa.user.store.UserFollowStore
import com.musinsa.user.vo.UserFollowOrderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserFollowServiceV1(
    private val userReader: UserReader,
    private val userFollowReader: UserFollowReader,
    private val userFollowStore: UserFollowStore,
    private val userFollowAssembler: UserFollowAssembler,
    private val userFollowConverter: UserFollowConverter
) : UserFollowService {

    @Transactional
    override fun following(userId: Long, followingUserId: Long): Boolean {
        val user: User = userReader.findById(id = userId)
        val followingUser: User = userReader.findById(id = followingUserId)

        val userFollow: UserFollow = userFollowAssembler.assemble(
            followerUser = user,
            followingUser = followingUser
        )

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

    @Transactional(readOnly = true)
    override fun getUserFollows(
        queryFilter: UserFollowQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserFollowOrderType>
    ): PaginationResponse {
        val userFollows: List<UserFollow> = userFollowReader.searchUserFollows(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = userFollowReader.searchUserFollowsCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = userFollowConverter.toResponseInBatch(userFollows = userFollows),
            totalCount = totalCount,
        )
    }
}
