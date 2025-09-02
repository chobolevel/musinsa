package com.musinsa.user.serivce

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UpdateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.entity.UserQueryFilter
import com.musinsa.user.vo.UserOrderType

interface UserService {

    fun createUser(request: CreateUserRequest): Long

    fun getUsers(
        queryFilter: UserQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserOrderType>
    ): PaginationResponse

    fun getUser(id: Long): UserResponse

    fun updateUser(userId: Long, request: UpdateUserRequest): Long

    fun resignUser(userId: Long): Boolean
}
