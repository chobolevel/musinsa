package com.musinsa.post.dto

import jakarta.validation.constraints.NotEmpty

data class CreatePostRequest(
    @field:NotEmpty
    val title: String,
    @field:NotEmpty
    val content: String
)
