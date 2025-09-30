package com.musinsa.snap

import com.musinsa.snap.dto.CreateSnapTagRequest
import com.musinsa.snap.dto.SnapTagResponse
import com.musinsa.snap.dto.UpdateSnapTagRequest
import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.vo.SnapTagType
import com.musinsa.snap.vo.SnapTagUpdateMask

object DummySnapTag {
    private val id: Long = 1L
    private val type: SnapTagType = SnapTagType.STYLE
    private val name: String = "캐주얼"
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummySnapTag: SnapTag by lazy {
        SnapTag(
            type = type,
            name = name,
        ).also {
            it.id = id
        }
    }

    private val dummyResponse: SnapTagResponse by lazy {
        SnapTagResponse(
            id = id,
            type = type,
            name = name,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    private val dummyCreateRequest: CreateSnapTagRequest by lazy {
        CreateSnapTagRequest(
            type = type,
            name = name
        )
    }

    private val dummyUpdateRequest: UpdateSnapTagRequest by lazy {
        UpdateSnapTagRequest(
            type = null,
            name = "변경",
            updateMask = listOf(SnapTagUpdateMask.NAME)
        )
    }

    fun toEntity(): SnapTag {
        return dummySnapTag
    }

    fun toResponse(): SnapTagResponse {
        return dummyResponse
    }

    fun toCreateRequest(): CreateSnapTagRequest {
        return dummyCreateRequest
    }

    fun toUpdateRequest(): UpdateSnapTagRequest {
        return dummyUpdateRequest
    }
}
