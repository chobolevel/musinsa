package com.musinsa.post

import com.musinsa.post.entity.Post

object DummyPost {
    private const val id: Long = 1L
    private const val title: String = "title"
    private const val content: String = "content"
    private const val createdAt: Long = 0L
    private const val updatedAt: Long = 0L

    private val dummyPost: Post by lazy {
        Post(
            title = title,
            content = content
        ).also { it.id = id }
    }

    fun toEntity(): Post = dummyPost
}
