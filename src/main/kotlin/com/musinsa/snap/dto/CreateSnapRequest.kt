package com.musinsa.snap.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.Size

data class CreateSnapRequest(
    val content: String?,
    @field:Size(min = 1)
    val snapTagIds: List<Long>,
    @field:Size(min = 1)
    @field:Valid
    val snapImages: List<CreateSnapImageRequest>
)
