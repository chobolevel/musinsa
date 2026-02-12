package com.musinsa.post.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.post.dto.UpdatePostImageRequest
import com.musinsa.post.vo.PostImageUpdateMask
import org.springframework.stereotype.Component

@Component
class PostImageParameterValidator {

    fun validate(request: UpdatePostImageRequest) {
        request.updateMask.forEach {
            when (it) {
                PostImageUpdateMask.TYPE -> {
                    if (request.type == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostImageUpdateMask.TYPE.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                PostImageUpdateMask.PATH -> {
                    if (request.path.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostImageUpdateMask.PATH.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                PostImageUpdateMask.WIDTH -> {
                    if (request.width == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostImageUpdateMask.WIDTH.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                PostImageUpdateMask.HEIGHT -> {
                    if (request.height == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostImageUpdateMask.HEIGHT.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                PostImageUpdateMask.SORT_ORDER -> {
                    if (request.sortOrder == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostImageUpdateMask.SORT_ORDER.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }
            }
        }
    }
}
