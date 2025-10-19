package com.musinsa.snap.converter

import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.entity.SnapComment
import org.springframework.stereotype.Component

@Component
class SnapCommentConverter {

    fun toEntity(request: CreateSnapCommentRequest): SnapComment {
        return SnapComment(
            comment = request.comment
        )
    }
}
