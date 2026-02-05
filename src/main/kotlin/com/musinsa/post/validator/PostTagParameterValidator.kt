package com.musinsa.post.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.post.dto.UpdatePostTagRequest
import com.musinsa.post.vo.PostTagUpdateMask
import org.springframework.stereotype.Component

@Component
class PostTagParameterValidator {

    fun validate(request: UpdatePostTagRequest) {
        request.updateMask.forEach {
            when (it) {
                PostTagUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostTagUpdateMask.NAME.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                PostTagUpdateMask.SORT_ORDER -> {
                    if (request.sortOrder == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostTagUpdateMask.SORT_ORDER.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }
            }
        }
    }
}
