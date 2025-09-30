package com.musinsa.snap.dto

import com.musinsa.snap.vo.SnapTagType
import com.musinsa.snap.vo.SnapTagUpdateMask
import jakarta.validation.constraints.Size

data class UpdateSnapTagRequest(
    val type: SnapTagType?,
    val name: String?,
    @field:Size(min = 1)
    val updateMask: List<SnapTagUpdateMask>
)
