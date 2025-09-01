package com.musinsa.user.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.user.dto.UpdateUserRequest
import com.musinsa.user.vo.UserUpdateMask
import org.springframework.stereotype.Component

@Component
class UserParameterValidator {

    fun validate(request: UpdateUserRequest) {
        request.updateMask.forEach {
            when (it) {
                UserUpdateMask.NICKNAME -> {
                    if (request.nickname.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 닉네임은 필수 값입니다."
                        )
                    }
                }
            }
        }
    }
}
