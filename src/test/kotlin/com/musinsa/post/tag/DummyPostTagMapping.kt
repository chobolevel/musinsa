package com.musinsa.post.tag

import com.musinsa.post.entity.PostTagMapping

object DummyPostTagMapping {
    private const val id: Long = 1L

    private val dummyPostTagMapping: PostTagMapping by lazy {
        PostTagMapping().also { it.id = id }
    }

    fun toEntity(): PostTagMapping = dummyPostTagMapping
}
