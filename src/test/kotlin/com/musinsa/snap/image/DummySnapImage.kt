package com.musinsa.snap.image

import com.musinsa.snap.dto.SnapImageResponse
import com.musinsa.snap.entity.SnapImage

object DummySnapImage {
    private val id: Long = 1L
    private val path: String = "/image/2025/06/02/c92caa4f-4240-4ddc-8c51-f235845103b9.jpeg"
    private val width: Int = 100
    private val height: Int = 100
    private val order: Int = 0
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummySnapImage: SnapImage by lazy {
        SnapImage(
            path = path,
            width = width,
            height = height,
            order = order,
        ).also {
            it.id = id
        }
    }

    private val dummySnapImageResponse: SnapImageResponse by lazy {
        SnapImageResponse(
            id = id,
            path = path,
            url = "https://chobolevel.s3.ap-northeast-2.amazonaws.comv$path",
            width = width,
            height = height,
            order = order,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    fun toEntity(): SnapImage = dummySnapImage

    fun toResponse(): SnapImageResponse = dummySnapImageResponse
}
