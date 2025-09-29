package com.musinsa.snap.dto

import com.musinsa.snap.vo.SnapTagType
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class CreateSnapTagRequest(
    @field:NotNull
    val type: SnapTagType,
    @field:NotEmpty
    val name: String,
)
