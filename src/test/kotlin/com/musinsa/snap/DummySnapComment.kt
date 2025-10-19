package com.musinsa.snap

import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.dto.SnapCommentResponse
import com.musinsa.snap.entity.SnapComment
import com.musinsa.user.DummyUser

object DummySnapComment {
    private val id: Long = 1L
    private val comment: String = "스타일이 너무 멋저요!"
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummySnapComment: SnapComment by lazy {
        SnapComment(
            comment = comment
        ).also {
            it.id = id
        }
    }

    private val dummySnapCommentResponse: SnapCommentResponse by lazy {
        SnapCommentResponse(
            id = id,
            snapId = DummySnap.toEntity().id!!,
            writer = DummyUser.toResponse(),
            comment = comment,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private val dummyCreateRequest: CreateSnapCommentRequest by lazy {
        CreateSnapCommentRequest(
            comment = comment
        )
    }

    fun toEntity(): SnapComment = dummySnapComment

    fun toResponse(): SnapCommentResponse = dummySnapCommentResponse

    fun toCreateRequest(): CreateSnapCommentRequest = dummyCreateRequest
}
