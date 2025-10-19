package com.musinsa.snap

import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.entity.SnapComment

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

    private val dummyCreateRequest: CreateSnapCommentRequest by lazy {
        CreateSnapCommentRequest(
            comment = comment
        )
    }

    fun toEntity(): SnapComment = dummySnapComment

    fun toCreateRequest(): CreateSnapCommentRequest = dummyCreateRequest
}
