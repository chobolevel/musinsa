package com.musinsa.snap.dto

import com.musinsa.snap.vo.SnapUpdateMask
import jakarta.validation.constraints.Size

data class UpdateSnapRequest(
    val content: String?,
    val snapImages: List<UpdateSnapImageRequest>?,
    @field:Size(min = 1)
    val updateMasks: List<SnapUpdateMask>
)
