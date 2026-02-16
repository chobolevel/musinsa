package com.musinsa.post.dto

import com.musinsa.post.vo.PostCommentUpdateMask
import jakarta.validation.constraints.Size

data class UpdatePostCommentRequest(
    val comment: String?,
    @field:Size(min = 1)
    val updateMask: List<PostCommentUpdateMask>
)
