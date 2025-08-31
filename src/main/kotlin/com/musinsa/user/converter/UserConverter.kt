package com.musinsa.user.converter

import com.musinsa.common.extension.toTimeStamp
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserConverter(
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun toEntity(request: CreateUserRequest): User {
        return User(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            nickname = request.nickname
        )
    }

    fun toResponse(user: User): UserResponse {
        return UserResponse(
            id = user.id!!,
            email = user.email,
            nickname = user.nickname,
            isResigned = user.resigned,
            createdAt = user.createdAt!!.toTimeStamp(),
            updatedAt = user.updatedAt!!.toTimeStamp()
        )
    }

    fun toResponseInBatch(users: List<User>): List<UserResponse> {
        return users.map { toResponse(it) }
    }
}
