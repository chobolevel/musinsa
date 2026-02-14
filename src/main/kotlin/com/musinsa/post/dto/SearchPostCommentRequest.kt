package com.musinsa.post.dto

import com.musinsa.post.vo.PostCommentOrderType

data class SearchPostCommentRequest(
    val userId: Long?,
    val postId: Long?,
    val parentId: Long?,
    val orderTypes: List<PostCommentOrderType>?
)
