package com.musinsa.snap.dto

import jakarta.validation.constraints.Size

data class CreateSnapRequest(
    val content: String?,
    @field:Size(min = 1)
    val snapImages: List<CreateSnapImageRequest>
)
