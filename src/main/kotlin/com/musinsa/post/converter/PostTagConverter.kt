package com.musinsa.post.converter

import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.entity.PostTag
import org.springframework.stereotype.Component

@Component
class PostTagConverter {

    fun toEntity(request: CreatePostTagRequest): PostTag {
        return PostTag(
            name = request.name
        )
    }
}
