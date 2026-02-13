package com.musinsa.post.converter

import com.musinsa.post.dto.CreatePostCommentRequest
import com.musinsa.post.entity.PostComment
import org.springframework.stereotype.Component

@Component
class PostCommentConverter {

    fun toEntity(request: CreatePostCommentRequest): PostComment {
        return PostComment(
            comment = request.comment
        )
    }
}
