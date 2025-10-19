package com.musinsa.snap

import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.dto.SnapCommentResponse
import com.musinsa.snap.dto.UpdateSnapCommentRequest
import com.musinsa.snap.entity.SnapComment
import com.musinsa.snap.vo.SnapCommentUpdateMask
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

    private val dummyUpdateRequest: UpdateSnapCommentRequest by lazy {
        UpdateSnapCommentRequest(
            comment = "사이즈는 어때요?",
            updateMasks = listOf(SnapCommentUpdateMask.COMMENT)
        )
    }

    fun toEntity(): SnapComment = dummySnapComment

    fun toResponse(): SnapCommentResponse = dummySnapCommentResponse

    fun toCreateRequest(): CreateSnapCommentRequest = dummyCreateRequest

    fun toUpdateRequest(): UpdateSnapCommentRequest = dummyUpdateRequest
}
