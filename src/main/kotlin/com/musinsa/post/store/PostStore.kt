package com.musinsa.post.store

import com.musinsa.post.entity.Post
import com.musinsa.post.repository.PostRepository
import org.springframework.stereotype.Component

@Component
class PostStore(
    private val postRepository: PostRepository,
) {

    fun save(post: Post): Post {
        return postRepository.save(post)
    }
}
