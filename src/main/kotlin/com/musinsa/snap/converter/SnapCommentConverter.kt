package com.musinsa.snap.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.dto.SnapCommentResponse
import com.musinsa.snap.entity.SnapComment
import com.musinsa.user.converter.UserConverter
import org.springframework.stereotype.Component

@Component
class SnapCommentConverter(
    private val userConverter: UserConverter,
) {

    fun toEntity(request: CreateSnapCommentRequest): SnapComment {
        return SnapComment(
            comment = request.comment
        )
    }

    fun toResponse(snapComment: SnapComment): SnapCommentResponse {
        return SnapCommentResponse(
            id = snapComment.id!!,
            snapId = snapComment.snap!!.id!!,
            writer = userConverter.toResponse(user = snapComment.user!!),
            comment = snapComment.comment,
            createdAt = snapComment.createdAt!!.toMillis(),
            updatedAt = snapComment.updatedAt!!.toMillis(),
        )
    }

    fun toResponseInBatch(snapComments: List<SnapComment>): List<SnapCommentResponse> {
        return snapComments.map { toResponse(it) }
    }
}
