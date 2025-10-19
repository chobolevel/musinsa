package com.musinsa.snap.dto

import com.musinsa.user.dto.UserResponse

data class SnapCommentResponse(
    val id: Long,
    val snapId: Long,
    val writer: UserResponse,
    val comment: String,
    val createdAt: Long,
    val updatedAt: Long
)
