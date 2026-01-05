package com.musinsa.snap.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.snap.dto.CreateSnapImageRequest
import com.musinsa.snap.dto.SnapImageResponse
import com.musinsa.snap.entity.SnapImage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SnapImageConverter(
    @Value("\${cloud.aws.s3.imageHost}")
    private val imageHost: String,
) {

    fun toEntity(request: CreateSnapImageRequest): SnapImage {
        return SnapImage(
            path = request.path,
            width = request.width,
            height = request.height,
            sortOrder = request.sortOrder
        )
    }

    fun toEntityInBatch(requests: List<CreateSnapImageRequest>): List<SnapImage> {
        return requests.map { request -> toEntity(request = request) }
    }

    fun toResponse(snapImage: SnapImage): SnapImageResponse {
        return SnapImageResponse(
            id = snapImage.id!!,
            path = snapImage.path,
            url = "$imageHost${snapImage.path}",
            width = snapImage.width,
            height = snapImage.height,
            sortOrder = snapImage.sortOrder,
            createdAt = snapImage.createdAt!!.toMillis(),
            updatedAt = snapImage.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(snapImages: List<SnapImage>): List<SnapImageResponse> {
        return snapImages.map { toResponse(it) }
    }
}
