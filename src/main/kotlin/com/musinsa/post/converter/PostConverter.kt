package com.musinsa.post.converter

import com.musinsa.post.dto.CreatePostRequest
import com.musinsa.post.entity.Post
import org.springframework.stereotype.Component

@Component
class PostConverter {

    fun toEntity(request: CreatePostRequest): Post {
        return Post(
            title = request.title,
            content = request.content
        )
    }
}
