package com.musinsa.post.dto

import com.musinsa.common.annotation.HtmlLength
import jakarta.validation.constraints.NotEmpty

data class CreatePostRequest(
    @field:NotEmpty
    val title: String,
    @field:NotEmpty
    @field:HtmlLength(min = 20)
    val content: String
)
