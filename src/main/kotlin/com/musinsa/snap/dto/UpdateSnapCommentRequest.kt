package com.musinsa.snap.dto

import com.musinsa.snap.vo.SnapCommentUpdateMask
import jakarta.validation.constraints.Size

data class UpdateSnapCommentRequest(
    val comment: String?,
    @field:Size(min = 1)
    val updateMasks: List<SnapCommentUpdateMask>
)
