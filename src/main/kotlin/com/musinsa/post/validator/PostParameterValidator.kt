package com.musinsa.post.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.post.dto.UpdatePostImageRequest
import com.musinsa.post.dto.UpdatePostRequest
import com.musinsa.post.vo.PostUpdateMask
import org.springframework.stereotype.Component

@Component
class PostParameterValidator(
    private val postImageParameterValidator: PostImageParameterValidator
) {

    fun validate(request: UpdatePostRequest) {
        request.updateMask.forEach {
            when (it) {
                PostUpdateMask.TAG -> {
                    if (request.postTagIds.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostUpdateMask.TAG.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                PostUpdateMask.TITLE -> {
                    if (request.title.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostUpdateMask.TITLE.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                    if (request.title.length < 5) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostUpdateMask.TITLE.fieldName}]은(는) 최소 5자 이상이어야 합니다."
                        )
                    }
                }

                PostUpdateMask.CONTENT -> {
                    if (request.content.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${PostUpdateMask.CONTENT.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                PostUpdateMask.IMAGES -> {
                    request.postImages?.filterIsInstance<UpdatePostImageRequest>()?.forEach { request ->
                        postImageParameterValidator.validate(request = request)
                    }
                }
            }
        }
    }
}
