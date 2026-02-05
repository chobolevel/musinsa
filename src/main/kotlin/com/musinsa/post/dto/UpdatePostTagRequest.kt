package com.musinsa.post.dto

import com.musinsa.post.vo.PostTagUpdateMask
import jakarta.validation.constraints.Size

data class UpdatePostTagRequest(
    val name: String?,
    val sortOrder: Int?,
    @field:Size(min = 1)
    val updateMask: List<PostTagUpdateMask>
)
