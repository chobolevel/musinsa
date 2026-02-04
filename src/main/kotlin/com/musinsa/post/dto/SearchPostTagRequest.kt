package com.musinsa.post.dto

import com.musinsa.post.vo.PostTagOrderType

data class SearchPostTagRequest(
    val name: String?,
    val orderTypes: List<PostTagOrderType>?
)
