package com.musinsa.snap.dto

import com.musinsa.snap.vo.SnapImageUpdateMask
import jakarta.validation.constraints.Size

data class UpdateSnapImageRequest(
    val id: Long,
    val path: String?,
    val width: Int?,
    val height: Int?,
    val sortOrder: Int?,
    @field:Size(min = 1)
    val updateMask: List<SnapImageUpdateMask>
) : SnapImageCommand
