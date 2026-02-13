package com.musinsa.post.comment

import com.musinsa.post.entity.PostComment

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

    private val dummyParentPostComment: PostComment by lazy {
        PostComment(
            comment = parentComment
        ).also { it.id = parentId }
    }

    fun toEntity(): PostComment = dummyPostComment

    fun toParentEntity(): PostComment = dummyParentPostComment
}
