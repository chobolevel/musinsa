package com.musinsa.post.dto

import com.musinsa.post.vo.PostOrderType

data class PostSearchRequest(
    val userId: Long?,
    val keyword: String?,
    val orderTypes: List<PostOrderType>?
)
