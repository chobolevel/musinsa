package com.musinsa.snap.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.snap.dto.CreateSnapRequest
import com.musinsa.snap.dto.SnapResponse
import com.musinsa.snap.entity.Snap
import com.musinsa.user.converter.UserConverter
import org.springframework.stereotype.Component

@Component
class SnapConverter(
    private val userConverter: UserConverter,
    private val snapImageConverter: SnapImageConverter,
) {

    fun toEntity(request: CreateSnapRequest): Snap {
        return Snap(
            content = request.content
        )
    }

    fun toResponse(snap: Snap): SnapResponse {
        return SnapResponse(
            id = snap.id!!,
            writer = userConverter.toResponse(user = snap.writer!!),
            content = snap.content,
            thumbnailImage = snapImageConverter.toResponse(snapImage = snap.snapImages[0]),
            images = snapImageConverter.toResponseInBatch(snapImages = snap.snapImages),
            likeCount = snap.snapLikes.size.toLong(),
            createdAt = snap.createdAt!!.toMillis(),
            updatedAt = snap.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(snaps: List<Snap>): List<SnapResponse> {
        return snaps.map { snap -> toResponse(snap) }
    }
}
