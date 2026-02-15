package com.musinsa.post.service

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.post.dto.CreatePostCommentRequest
import com.musinsa.post.dto.PostCommentResponse
import com.musinsa.post.reader.PostCommentQueryFilter
import com.musinsa.post.vo.PostCommentOrderType

interface PostCommentService {

    fun createPostComment(
        userId: Long,
        postId: Long,
        request: CreatePostCommentRequest
    ): Long

    fun getPostComments(
        queryFilter: PostCommentQueryFilter,
        pagination: Pagination,
        orderTypes: List<PostCommentOrderType>
    ): PaginationResponse

    fun getPostComment(postCommentId: Long): PostCommentResponse
}
