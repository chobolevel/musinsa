package com.musinsa.user.serivce

import com.musinsa.user.dto.CreateUserRequest

interface UserService {

    fun createUser(request: CreateUserRequest): Long
}
