package com.musinsa.user.service

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.user.reader.UserFollowQueryFilter
import com.musinsa.user.vo.UserFollowOrderType

interface UserFollowService {

    fun following(userId: Long, followingUserId: Long): Boolean

    fun unFollowing(userId: Long, unFollowingUserId: Long): Boolean

    fun getUserFollows(
        queryFilter: UserFollowQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserFollowOrderType>
    ): PaginationResponse
}
