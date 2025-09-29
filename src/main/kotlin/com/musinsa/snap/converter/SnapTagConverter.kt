package com.musinsa.snap.converter

import com.musinsa.snap.dto.CreateSnapTagRequest
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
}
