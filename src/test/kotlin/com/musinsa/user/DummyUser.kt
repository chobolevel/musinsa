package com.musinsa.user

import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.entity.User

object DummyUser {
    private val id: Long = 1L
    private val email: String = "rodaka123@naver.com"
    private val password: String = "rkddlswo218@"
    private val nickname: String = "알감자"
    private val resigned: Boolean = false
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummyCreateRequest: CreateUserRequest = CreateUserRequest(
        email = email,
        password = password,
        nickname = nickname,
    )

    private val dummyEntity: User = User(
        email = email,
        password = password,
        nickname = nickname
    ).also { it.id = id }

    private val dummyResponse: UserResponse = UserResponse(
        id = id,
        email = email,
        nickname = nickname,
        isResigned = resigned,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

    fun toCreateRequest(): CreateUserRequest {
        return dummyCreateRequest
    }

    fun toEntity(): User {
        return dummyEntity
    }

    fun toResponse(): UserResponse {
        return dummyResponse
    }
}
