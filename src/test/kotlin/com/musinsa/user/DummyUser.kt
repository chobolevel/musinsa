package com.musinsa.user

import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UpdateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.entity.User
import com.musinsa.user.vo.UserUpdateMask

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

    private val dummyUpdateRequest: UpdateUserRequest = UpdateUserRequest(
        nickname = "왕감자",
        updateMask = listOfNotNull(UserUpdateMask.NICKNAME)
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

    fun toUpdateRequest(): UpdateUserRequest {
        return dummyUpdateRequest
    }
}
