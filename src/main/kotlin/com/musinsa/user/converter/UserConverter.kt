package com.musinsa.user.converter

import com.musinsa.user.dto.CreateUserRequest
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
}
