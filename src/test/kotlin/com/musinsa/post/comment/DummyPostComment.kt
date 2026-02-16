package com.musinsa.post.comment

import com.musinsa.post.DummyPost
import com.musinsa.post.dto.CreatePostCommentRequest
import com.musinsa.post.dto.PostCommentResponse
import com.musinsa.post.dto.UpdatePostCommentRequest
import com.musinsa.post.entity.PostComment
import com.musinsa.post.vo.PostCommentUpdateMask
import com.musinsa.user.DummyUser

object DummyPostComment {
    private const val id: Long = 1L
    private const val comment: String = "테스트 댓글"
    private const val createdAt: Long = 0L
    private const val updatedAt: Long = 0L

    private const val parentId: Long = 2L
    private const val parentComment: String = "부모 테스트 댓글"
    private const val parentCreatedAt: Long = 0L
    private const val parentUpdatedAt: Long = 0L

    private val dummyPostComment: PostComment by lazy {
        PostComment(
            comment = comment
        ).also { it.id = id }
    }

    private val dummyPostCommentResponse: PostCommentResponse by lazy {
        PostCommentResponse(
            id = id,
            user = DummyUser.toResponse(),
            postId = DummyPost.toEntity().id!!,
            parentId = null,
            comment = comment,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    private val dummyParentPostComment: PostComment by lazy {
        PostComment(
            comment = parentComment
        ).also { it.id = parentId }
    }

    private val dummyCreateRequest: CreatePostCommentRequest by lazy {
        CreatePostCommentRequest(
            parentId = parentId,
            comment = comment
        )
    }

    private val dummyUpdateRequest: UpdatePostCommentRequest by lazy {
        UpdatePostCommentRequest(
            comment = "수정된 테스트 댓글",
            updateMask = listOf(PostCommentUpdateMask.COMMENT)
        )
    }

    fun toEntity(): PostComment = dummyPostComment

    fun toResponse(): PostCommentResponse = dummyPostCommentResponse

    fun toParentEntity(): PostComment = dummyParentPostComment

    fun toCreateRequest(): CreatePostCommentRequest = dummyCreateRequest

    fun toUpdateRequest(): UpdatePostCommentRequest = dummyUpdateRequest
}
