package com.musinsa.post.tag

import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.entity.PostTag

object DummyPostTag {
    private const val id: Long = 1L
    private const val name: String = "Java"
    private const val createdAt: Long = 0L
    private const val updatedAt: Long = 0L

    private val dummyPostTag: PostTag by lazy {
        PostTag(
            name = name
        ).also { it.id = id }
    }

    private val dummyCreateRequest: CreatePostTagRequest by lazy {
        CreatePostTagRequest(
            name = name
        )
    }

    fun toEntity(): PostTag = dummyPostTag

    fun toCreateRequest(): CreatePostTagRequest = dummyCreateRequest
}
