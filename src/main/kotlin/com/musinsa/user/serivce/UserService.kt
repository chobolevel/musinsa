package com.musinsa.user.serivce

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.entity.UserOrderType
import com.musinsa.user.entity.UserQueryFilter

interface UserService {

    fun createUser(request: CreateUserRequest): Long

    fun getUsers(
        queryFilter: UserQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserOrderType>
    ): PaginationResponse
}
