package com.musinsa.post.service

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.reader.PostTagQueryFilter
import com.musinsa.post.vo.PostTagOrderType

interface PostTagService {

    fun createPostTag(request: CreatePostTagRequest): Long

    fun getPostTags(
        queryFilter: PostTagQueryFilter,
        pagination: Pagination,
        orderTypes: List<PostTagOrderType>
    ): PaginationResponse
}
