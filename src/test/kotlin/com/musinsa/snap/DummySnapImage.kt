package com.musinsa.snap

import com.musinsa.snap.entity.SnapImage

object DummySnapImage {
    private val id: Long = 1L
    private val url: String = "https://chobolevel.s3.ap-northeast-2.amazonaws.com/image/2025/06/02/c92caa4f-4240-4ddc-8c51-f235845103b9.jpeg"
    private val width: Int = 100
    private val height: Int = 100
    private val order: Int = 0
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummySnapImage: SnapImage by lazy {
        SnapImage(
            url = url,
            width = width,
            height = height,
            order = order,
        ).also {
            it.id = id
        }
    }

    fun toEntity(): SnapImage {
        return dummySnapImage
    }
}
