package com.musinsa.snap.dto

import com.musinsa.snap.vo.SnapUpdateMask
import jakarta.validation.constraints.Size

data class UpdateSnapRequest(
    val content: String?,
    @field:Size(min = 1)
    val updateMasks: List<SnapUpdateMask>
)
