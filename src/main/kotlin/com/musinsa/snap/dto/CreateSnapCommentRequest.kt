package com.musinsa.snap.dto

import jakarta.validation.constraints.NotEmpty

data class CreateSnapCommentRequest(
    val parentId: Long?,
    @field:NotEmpty
    val comment: String
)
