package com.musinsa.post.repository

import com.musinsa.post.entity.Post
import org.springframework.stereotype.Component

@Component
class PostRepositoryFacade(
    private val repository: PostRepository,
    private val customRepository: PostCustomRepository
) {

    fun save(post: Post): Post {
        return repository.save(post)
    }
}
