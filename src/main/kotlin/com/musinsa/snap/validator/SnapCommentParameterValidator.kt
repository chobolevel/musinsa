package com.musinsa.snap.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.snap.dto.UpdateSnapCommentRequest
import com.musinsa.snap.vo.SnapCommentUpdateMask
import org.springframework.stereotype.Component

@Component
class SnapCommentParameterValidator {

    fun validate(request: UpdateSnapCommentRequest) {
        request.updateMasks.forEach {
            when (it) {
                SnapCommentUpdateMask.COMMENT -> {
                    if (request.comment.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[comment]은(는) 필수 값입니다."
                        )
                    }
                }
            }
        }
    }
}
