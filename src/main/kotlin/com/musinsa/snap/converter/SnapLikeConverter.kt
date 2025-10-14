package com.musinsa.snap.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.snap.dto.SnapLikeResponse
import com.musinsa.snap.entity.SnapLike
import com.musinsa.user.converter.UserConverter
import org.springframework.stereotype.Component

@Component
class SnapLikeConverter(
    private val userConverter: UserConverter,
) {

    fun toResponse(snapLike: SnapLike): SnapLikeResponse {
        return SnapLikeResponse(
            id = snapLike.id!!,
            snapId = snapLike.snap!!.id!!,
            user = userConverter.toResponse(user = snapLike.user!!),
            createdAt = snapLike.createdAt!!.toMillis(),
            updatedAt = snapLike.updatedAt!!.toMillis(),
        )
    }

    fun toResponseInBatch(snapLikes: List<SnapLike>): List<SnapLikeResponse> {
        return snapLikes.map { toResponse(it) }
    }
}
