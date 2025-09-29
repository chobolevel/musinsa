package com.musinsa.snap.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.snap.dto.CreateSnapTagRequest
import com.musinsa.snap.dto.SnapTagResponse
import com.musinsa.snap.entity.SnapTag
import org.springframework.stereotype.Component

@Component
class SnapTagConverter {

    fun toEntity(request: CreateSnapTagRequest): SnapTag {
        return SnapTag(
            type = request.type,
            name = request.name
        )
    }

    fun toResponse(entity: SnapTag): SnapTagResponse {
        return SnapTagResponse(
            id = entity.id!!,
            type = entity.type,
            name = entity.name,
            createdAt = entity.createdAt!!.toMillis(),
            updatedAt = entity.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(entities: List<SnapTag>): List<SnapTagResponse> {
        return entities.map { toResponse(it) }
    }
}
