package com.musinsa.post.dto

import jakarta.validation.constraints.NotEmpty

data class CreatePostCommentRequest(
    val parentId: Long?,
    @field:NotEmpty
    val comment: String,
)
