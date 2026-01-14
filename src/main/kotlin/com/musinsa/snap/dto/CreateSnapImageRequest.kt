package com.musinsa.snap.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class CreateSnapImageRequest(
    @field:NotEmpty
    val path: String,
    @field:NotNull
    val width: Int,
    @field:NotNull
    val height: Int,
    @field:NotNull
    val sortOrder: Int,
) : SnapImageCommand
