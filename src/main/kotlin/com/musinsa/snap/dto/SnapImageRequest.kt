package com.musinsa.snap.dto

import jakarta.validation.constraints.NotEmpty

data class SnapImageRequest(
    @field:NotEmpty
    val path: String,
    val width: Int = 0,
    val height: Int = 0,
    val order: Int = 0,
)
