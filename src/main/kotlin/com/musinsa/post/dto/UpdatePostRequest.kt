package com.musinsa.post.dto

import com.musinsa.common.annotation.HtmlLength
import com.musinsa.post.vo.PostUpdateMask
import jakarta.validation.constraints.Size

data class UpdatePostRequest(
    val postTagIds: Set<Long>?,
    val title: String?,
    @field:HtmlLength(min = 20)
    val content: String?,
    @field:Size(min = 1)
    val updateMask: List<PostUpdateMask>
)
