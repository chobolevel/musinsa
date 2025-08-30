package com.musinsa.user.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.PolicyViolationException
import com.musinsa.user.entity.UserRepositoryFacade
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class UserBusinessValidator(
    private val repository: UserRepositoryFacade
) {

    @Throws(PolicyViolationException::class)
    fun validateEmailExist(email: String) {
        if (repository.existsByEmail(email = email)) {
            throw PolicyViolationException(
                errorCode = ErrorCode.EMAIL_ALREADY_EXIST,
                message = ErrorCode.EMAIL_ALREADY_EXIST.defaultMessage
            )
        }
    }

    @kotlin.Throws(PolicyViolationException::class)
    fun validateNicknameExist(nickname: String) {
        if (repository.existsByNickname(nickname = nickname)) {
            throw PolicyViolationException(
                errorCode = ErrorCode.NICKNAME_ALREADY_EXISTS,
                message = ErrorCode.NICKNAME_ALREADY_EXISTS.defaultMessage
            )
        }
    }
}
