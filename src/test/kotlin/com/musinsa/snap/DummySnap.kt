package com.musinsa.snap

import com.musinsa.snap.dto.CreateSnapRequest
import com.musinsa.snap.dto.SnapResponse
import com.musinsa.snap.dto.UpdateSnapRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.vo.SnapUpdateMask
import com.musinsa.user.DummyUser

object DummySnap {
    private val id: Long = 1L
    private val content: String = "오늘 날씨는 맑음!"
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummySnap: Snap by lazy {
        Snap(
            content = content
        ).also {
            it.id = id
        }
    }

    private val dummySnapResponse: SnapResponse by lazy {
        SnapResponse(
            id = id,
            writer = DummyUser.toResponse(),
            content = content,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    private val dummyCreateRequest: CreateSnapRequest by lazy {
        CreateSnapRequest(
            content = content,
            snapImages = emptyList()
        )
    }

    private val dummyUpdateRequest: UpdateSnapRequest by lazy {
        UpdateSnapRequest(
            content = null,
            snapImages = null,
            updateMasks = listOfNotNull(SnapUpdateMask.CONTENT)
        )
    }

    fun toEntity(): Snap {
        return dummySnap
    }

    fun toResponse(): SnapResponse {
        return dummySnapResponse
    }

    fun toCreateRequest(): CreateSnapRequest {
        return dummyCreateRequest
    }

    fun toUpdateRequest(): UpdateSnapRequest {
        return dummyUpdateRequest
    }
}
