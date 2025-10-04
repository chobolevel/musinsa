package com.musinsa.snap.converter

import com.musinsa.snap.dto.CreateSnapRequest
import com.musinsa.snap.entity.Snap
import org.springframework.stereotype.Component

@Component
class SnapConverter {

    fun toEntity(request: CreateSnapRequest): Snap {
        return Snap(
            content = request.content
        )
    }
}
