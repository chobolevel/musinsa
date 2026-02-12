package com.musinsa.post.converter

import com.musinsa.post.dto.CreatePostImageRequest
import com.musinsa.post.entity.PostImage
import org.springframework.stereotype.Component

@Component
class PostImageConverter {

    fun toEntity(request: CreatePostImageRequest): PostImage {
        return PostImage(
            type = request.type,
            path = request.path,
            width = request.width ?: 0,
            height = request.height ?: 0,
            sortOrder = request.sortOrder ?: 10
        )
    }

    fun toEntityInBatch(requests: List<CreatePostImageRequest>): List<PostImage> {
        return requests.map { request -> toEntity(request) }
    }
}
