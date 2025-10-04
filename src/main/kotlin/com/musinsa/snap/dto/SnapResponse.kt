package com.musinsa.snap.dto

import com.musinsa.user.dto.UserResponse

data class SnapResponse(
    val id: Long,
    val writer: UserResponse,
    val content: String?,
    val createdAt: Long,
    val updatedAt: Long
)
