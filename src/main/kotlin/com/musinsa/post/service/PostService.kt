package com.musinsa.post.service

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.post.dto.CreatePostRequest
import com.musinsa.post.reader.PostQueryFilter
import com.musinsa.post.vo.PostOrderType

interface PostService {

    fun createPost(userId: Long, request: CreatePostRequest): Long

    fun getPosts(
        queryFilter: PostQueryFilter,
        pagination: Pagination,
        orderTypes: List<PostOrderType>
    ): PaginationResponse
}
