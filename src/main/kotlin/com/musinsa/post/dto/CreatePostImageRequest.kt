package com.musinsa.post.dto

import com.musinsa.post.vo.PostImageType
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class CreatePostImageRequest(
    @field:NotNull
    val type: PostImageType,
    @field:NotEmpty
    val path: String,
    val width: Int?,
    val height: Int?,
    val sortOrder: Int?
)
