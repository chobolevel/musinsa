package com.musinsa.post.store

import com.musinsa.post.entity.PostComment
import com.musinsa.post.repository.PostCommentRepository
import org.springframework.stereotype.Component

@Component
class PostCommentStore(
    private val postCommentRepository: PostCommentRepository
) {

    fun save(postComment: PostComment): PostComment {
        return postCommentRepository.save(postComment)
    }
}
