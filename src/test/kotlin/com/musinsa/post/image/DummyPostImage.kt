package com.musinsa.post.image

import com.musinsa.post.dto.CreatePostImageRequest
import com.musinsa.post.entity.PostImage
import com.musinsa.post.vo.PostImageType

object DummyPostImage {
    private const val id: Long = 1L
    private val type: PostImageType = PostImageType.THUMB_NAIL
    private const val path: String = "/image/2025/06/02/c92caa4f-4240-4ddc-8c51-f235845103b9.jpeg"
    private const val width: Int = 100
    private const val height: Int = 100
    private const val sortOrder: Int = 0
    private const val createdAt: Long = 0L
    private const val updatedAt: Long = 0L

    private val dummyPostImage: PostImage by lazy {
        PostImage(
            type = type,
            path = path,
            width = width,
            height = height,
            sortOrder = sortOrder
        ).also { it.id = id }
    }

    private val createRequest: CreatePostImageRequest by lazy {
        CreatePostImageRequest(
            type = type,
            path = path,
            width = width,
            height = height,
            sortOrder = sortOrder
        )
    }

    fun toEntity(): PostImage = dummyPostImage

    fun toCreateRequest(): CreatePostImageRequest = createRequest
}
