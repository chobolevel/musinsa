package com.musinsa.post.dto

import com.musinsa.common.annotation.HtmlLength
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class CreatePostRequest(
    @field:Size(min = 1)
    val postTagIds: Set<Long>,
    @field:NotEmpty
    val title: String,
    @field:NotEmpty
    @field:HtmlLength(min = 20)
    val content: String
)
