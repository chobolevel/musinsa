package com.musinsa.post.service

import com.musinsa.post.dto.CreatePostRequest

interface PostService {

    fun createPost(userId: Long, request: CreatePostRequest): Long
}
