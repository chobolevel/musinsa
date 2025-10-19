package com.musinsa.snap

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

    fun toEntity(): SnapComment = dummySnapComment
}
