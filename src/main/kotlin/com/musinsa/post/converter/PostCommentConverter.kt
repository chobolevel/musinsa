package com.musinsa.post.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.post.dto.CreatePostCommentRequest
import com.musinsa.post.dto.PostCommentResponse
import com.musinsa.post.entity.PostComment
import com.musinsa.user.converter.UserConverter
import org.springframework.stereotype.Component

@Component
class PostCommentConverter(
    private val userConverter: UserConverter,
) {

    fun toEntity(request: CreatePostCommentRequest): PostComment {
        return PostComment(
            comment = request.comment
        )
    }

    fun toResponse(postComment: PostComment): PostCommentResponse {
        return PostCommentResponse(
            id = postComment.id!!,
            user = userConverter.toResponse(user = postComment.user!!),
            postId = postComment.post!!.id!!,
            parentId = postComment.parent?.id!!,
            comment = postComment.comment,
            createdAt = postComment.createdAt!!.toMillis(),
            updatedAt = postComment.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(postComments: List<PostComment>): List<PostCommentResponse> {
        return postComments.map { toResponse(it) }
    }
}
