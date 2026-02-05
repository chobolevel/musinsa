package com.musinsa.post.tag

import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.dto.PostTagResponse
import com.musinsa.post.dto.UpdatePostTagRequest
import com.musinsa.post.entity.PostTag
import com.musinsa.post.vo.PostTagUpdateMask

object DummyPostTag {
    private const val id: Long = 1L
    private const val name: String = "Java"
    private const val sortOrder: Int = 0
    private const val createdAt: Long = 0L
    private const val updatedAt: Long = 0L

    private val dummyPostTag: PostTag by lazy {
        PostTag(
            name = name,
            sortOrder = sortOrder
        ).also { it.id = id }
    }

    private val dummyPostTagResponse: PostTagResponse by lazy {
        PostTagResponse(
            id = id,
            name = name,
            sortOrder = sortOrder,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    private val dummyCreateRequest: CreatePostTagRequest by lazy {
        CreatePostTagRequest(
            name = name,
            sortOrder = sortOrder
        )
    }

    private val dummyUpdateRequest: UpdatePostTagRequest by lazy {
        UpdatePostTagRequest(
            name = "Kotlin",
            sortOrder = null,
            updateMask = listOf(PostTagUpdateMask.NAME)
        )
    }

    fun toEntity(): PostTag = dummyPostTag

    fun toResponse(): PostTagResponse = dummyPostTagResponse

    fun toCreateRequest(): CreatePostTagRequest = dummyCreateRequest

    fun toUpdateRequest(): UpdatePostTagRequest = dummyUpdateRequest
}
