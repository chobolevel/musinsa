package com.musinsa.user.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UpdateUserRequest
import com.musinsa.user.vo.UserSignUpType
import com.musinsa.user.vo.UserUpdateMask
import org.springframework.stereotype.Component

@Component
class UserParameterValidator {

    fun validate(request: CreateUserRequest) {
        when (request.signUpType) {
            UserSignUpType.GENERAL -> {
                if (request.username.isNullOrEmpty()) {
                    throw InvalidParameterException(
                        errorCode = ErrorCode.INVALID_PARAMETER,
                        message = "[username]은(는) 필수 값입니다ㅑ."
                    )
                }
                if (request.password.isNullOrEmpty()) {
                    throw InvalidParameterException(
                        errorCode = ErrorCode.INVALID_PARAMETER,
                        message = "[password]은(는) 필수 값입니다."
                    )
                }
            }

            else -> {
                if (request.socialId.isNullOrEmpty()) {
                    throw InvalidParameterException(
                        errorCode = ErrorCode.INVALID_PARAMETER,
                        message = "[social_id]은(는) 필수 값입니다."
                    )
                }
            }
        }
    }

    fun validate(request: UpdateUserRequest) {
        request.updateMask.forEach {
            when (it) {
                UserUpdateMask.EMAIL -> {
                    if (request.email.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${UserUpdateMask.EMAIL.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                UserUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${UserUpdateMask.NAME.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                UserUpdateMask.PHONE -> {
                    if (request.phone.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.PHONE_NUMBER_ALREADY_EXISTS,
                            message = "[${UserUpdateMask.PHONE.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                UserUpdateMask.GENDER -> {
                    if (request.gender == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${UserUpdateMask.GENDER.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                UserUpdateMask.BIRTH_DATE -> {
                    if (request.birthDate == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${UserUpdateMask.BIRTH_DATE.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }
            }
        }
    }
}
