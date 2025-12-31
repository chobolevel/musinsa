package com.musinsa.snap.like

import com.musinsa.snap.DummySnap
import com.musinsa.snap.dto.SnapLikeResponse
import com.musinsa.snap.entity.SnapLike
import com.musinsa.user.DummyUser

object DummySnapLike {
    private val id: Long = 1L
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummySnapLike: SnapLike by lazy {
        SnapLike().also {
            it.id = id
        }
    }

    private val dummySnapLikeResponse: SnapLikeResponse by lazy {
        SnapLikeResponse(
            id = id,
            snapId = DummySnap.toEntity().id!!,
            user = DummyUser.toResponse(),
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    fun toEntity(): SnapLike {
        return dummySnapLike
    }

    fun toResponse(): SnapLikeResponse {
        return dummySnapLikeResponse
    }
}
