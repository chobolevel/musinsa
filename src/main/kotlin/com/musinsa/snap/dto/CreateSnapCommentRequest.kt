package com.musinsa.snap.dto

import jakarta.validation.constraints.NotEmpty

data class CreateSnapCommentRequest(
    @field:NotEmpty
    val comment: String
)
