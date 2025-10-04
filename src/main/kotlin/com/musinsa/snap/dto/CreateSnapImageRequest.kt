package com.musinsa.snap.dto

import jakarta.validation.constraints.NotEmpty

data class CreateSnapImageRequest(
    @field:NotEmpty
    val url: String,
    val width: Int = 0,
    val height: Int = 0,
    val order: Int = 0,
)
