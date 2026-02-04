package com.musinsa.post.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.dto.PostTagResponse
import com.musinsa.post.entity.PostTag
import org.springframework.stereotype.Component

@Component
class PostTagConverter {

    fun toEntity(request: CreatePostTagRequest): PostTag {
        return PostTag(
            name = request.name,
            sortOrder = request.sortOrder ?: 10,
        )
    }

    fun toResponse(postTag: PostTag): PostTagResponse {
        return PostTagResponse(
            id = postTag.id!!,
            name = postTag.name,
            sortOrder = postTag.sortOrder,
            createdAt = postTag.createdAt!!.toMillis(),
            updatedAt = postTag.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(postTags: List<PostTag>): List<PostTagResponse> {
        return postTags.map { toResponse(it) }
    }
}
