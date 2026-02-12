package com.musinsa.post.dto

import com.musinsa.post.vo.PostImageType
import com.musinsa.post.vo.PostImageUpdateMask
import jakarta.validation.constraints.Size

data class UpdatePostImageRequest(
    val id: Long,
    val type: PostImageType?,
    val path: String?,
    val width: Int?,
    val height: Int?,
    val sortOrder: Int?,
    @field:Size(min = 1)
    val updateMask: List<PostImageUpdateMask>
) : PostImageCommand
