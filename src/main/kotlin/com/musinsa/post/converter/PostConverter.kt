package com.musinsa.post.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.post.dto.CreatePostRequest
import com.musinsa.post.dto.PostResponse
import com.musinsa.post.entity.Post
import com.musinsa.user.entity.User
import org.springframework.stereotype.Component

@Component
class PostConverter {

    fun toEntity(request: CreatePostRequest): Post {
        return Post(
            title = request.title,
            content = request.content
        )
    }

    fun toResponse(post: Post): PostResponse {
        val user: User = post.user!!
        return PostResponse(
            id = post.id!!,
            userId = user.id!!,
            userName = user.name,
            title = post.title,
            content = post.content,
            createdAt = post.createdAt!!.toMillis(),
            updatedAt = post.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(posts: List<Post>): List<PostResponse> {
        return posts.map { toResponse(it) }
    }
}
