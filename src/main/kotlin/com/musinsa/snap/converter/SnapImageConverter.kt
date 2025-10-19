package com.musinsa.snap.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.snap.dto.SnapImageResponse
import com.musinsa.snap.entity.SnapImage
import org.springframework.stereotype.Component

@Component
class SnapImageConverter {

    fun toResponse(snapImage: SnapImage): SnapImageResponse {
        return SnapImageResponse(
            id = snapImage.id!!,
            url = snapImage.url,
            width = snapImage.width,
            height = snapImage.height,
            order = snapImage.order,
            createdAt = snapImage.createdAt!!.toMillis(),
            updatedAt = snapImage.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(snapImages: List<SnapImage>): List<SnapImageResponse> {
        return snapImages.map { toResponse(it) }
    }
}
