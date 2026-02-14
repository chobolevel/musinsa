package com.musinsa.post.dto

import com.musinsa.user.dto.UserResponse

data class PostCommentResponse(
    val id: Long,
    val user: UserResponse,
    val postId: Long,
    val parentId: Long?,
    val comment: String,
    val createdAt: Long,
    val updatedAt: Long,
)
