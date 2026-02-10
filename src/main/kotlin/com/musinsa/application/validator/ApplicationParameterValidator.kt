package com.musinsa.application.validator

import com.musinsa.application.dto.UpdateApplicationRequest
import com.musinsa.application.vo.ApplicationUpdateMask
import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import org.springframework.stereotype.Component

@Component
class ApplicationParameterValidator {

    fun validate(request: UpdateApplicationRequest) {
        request.updateMask.forEach {
            when (it) {
                ApplicationUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ApplicationUpdateMask.NAME.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }
            }
        }
    }
}
