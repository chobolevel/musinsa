package com.musinsa.user.dto

data class UserResponse(
    val id: Long,
    val email: String,
    val nickname: String,
    val isResigned: Boolean,
    val createdAt: Long,
    val updatedAt: Long,
)
