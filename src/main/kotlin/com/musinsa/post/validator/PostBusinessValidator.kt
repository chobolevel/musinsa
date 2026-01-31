package com.musinsa.post.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.PolicyViolationException
import com.musinsa.post.entity.Post
import org.springframework.stereotype.Component

@Component
class PostBusinessValidator {

    fun validateWriter(
        userId: Long,
        post: Post
    ) {
        if (userId != post.user?.id) {
            throw PolicyViolationException(
                errorCode = ErrorCode.ACCESSIBLE_ONLY_WRITER_ON_POST,
                message = ErrorCode.ACCESSIBLE_ONLY_WRITER_ON_POST.defaultMessage
            )
        }
    }
}
