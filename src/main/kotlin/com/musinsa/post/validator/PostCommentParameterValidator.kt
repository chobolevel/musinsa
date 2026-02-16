package com.musinsa.post.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.post.dto.UpdatePostCommentRequest
import com.musinsa.post.vo.PostCommentUpdateMask
import org.springframework.stereotype.Component

@Component
class PostCommentParameterValidator {

    fun validate(request: UpdatePostCommentRequest) {
        request.updateMask.forEach {
            when (it) {
                PostCommentUpdateMask.COMMENT -> {
                    if (request.comment.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostCommentUpdateMask.COMMENT.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }
            }
        }
    }
}
