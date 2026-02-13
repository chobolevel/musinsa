package com.musinsa.post.service

import com.musinsa.post.dto.CreatePostCommentRequest

interface PostCommentService {

    fun createPostComment(
        userId: Long,
        postId: Long,
        request: CreatePostCommentRequest
    ): Long
}
