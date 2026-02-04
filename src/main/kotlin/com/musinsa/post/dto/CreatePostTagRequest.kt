package com.musinsa.post.dto

import jakarta.validation.constraints.NotEmpty

data class CreatePostTagRequest(
    @field:NotEmpty
    val name: String,
)
