package com.musinsa.snap.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.PolicyViolationException
import com.musinsa.snap.entity.SnapComment
import org.springframework.stereotype.Component

@Component
class SnapCommentBusinessValidator {

    @Throws(PolicyViolationException::class)
    fun validateWriter(userId: Long, snapComment: SnapComment) {
        if (userId != snapComment.user!!.id) {
            throw PolicyViolationException(
                errorCode = ErrorCode.ACCESSIBLE_ONLY_SNAP_COMMENT_WRITER,
                message = ErrorCode.ACCESSIBLE_ONLY_SNAP_COMMENT_WRITER.defaultMessage
            )
        }
    }
}
