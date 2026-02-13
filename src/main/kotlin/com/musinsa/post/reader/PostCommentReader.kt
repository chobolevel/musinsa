package com.musinsa.post.reader

import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.post.entity.PostComment
import com.musinsa.post.repository.PostCommentRepository
import org.springframework.stereotype.Component

@Component
class PostCommentReader(
    private val postCommentRepository: PostCommentRepository
) {

    fun findById(id: Long): PostComment {
        return postCommentRepository.findByIdAndIsDeletedFalse(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.POST_COMMENT_NOT_FOUND,
            message = ErrorCode.POST_COMMENT_NOT_FOUND.defaultMessage
        )
    }
}
