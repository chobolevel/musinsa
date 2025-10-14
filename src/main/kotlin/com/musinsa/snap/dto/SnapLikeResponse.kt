package com.musinsa.snap.dto

import com.musinsa.user.dto.UserResponse

data class SnapLikeResponse(
    val id: Long,
    val snapId: Long,
    val user: UserResponse,
    val createdAt: Long,
    val updatedAt: Long
)
