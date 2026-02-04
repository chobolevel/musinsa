package com.musinsa.post.store

import com.musinsa.post.entity.PostTag
import com.musinsa.post.repository.PostTagRepository
import org.springframework.stereotype.Component

@Component
class PostTagStore(
    private val postTagRepository: PostTagRepository
) {

    fun save(postTag: PostTag): PostTag {
        return postTagRepository.save(postTag)
    }
}
