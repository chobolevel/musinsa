package com.musinsa.post.service

import com.musinsa.post.dto.CreatePostTagRequest

interface PostTagService {

    fun createPostTag(request: CreatePostTagRequest): Long
}
