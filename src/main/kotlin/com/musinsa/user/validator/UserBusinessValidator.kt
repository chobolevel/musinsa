package com.musinsa.user.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.PolicyViolationException
import com.musinsa.user.reader.UserReader
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class UserBusinessValidator(
    private val userReader: UserReader,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    @Throws(PolicyViolationException::class)
    fun validateUsernameExists(username: String) {
        if (userReader.existsByUsername(username)) {
            throw PolicyViolationException(
                errorCode = ErrorCode.USERNAME_ALREADY_EXISTS,
                message = ErrorCode.USERNAME_ALREADY_EXISTS.defaultMessage
            )
        }
    }

    @Throws(PolicyViolationException::class)
    fun validateSocialIdExists(socialId: String) {
        if (userReader.existsBySocialId(socialId)) {
            throw PolicyViolationException(
                errorCode = ErrorCode.SOCIAL_ID_ALREADY_EXISTS,
                message = ErrorCode.SOCIAL_ID_ALREADY_EXISTS.defaultMessage
            )
        }
    }

    @Throws(PolicyViolationException::class)
    fun validateEmailExist(email: String) {
        if (userReader.existsByEmail(email = email)) {
            throw PolicyViolationException(
                errorCode = ErrorCode.EMAIL_ALREADY_EXIST,
                message = ErrorCode.EMAIL_ALREADY_EXIST.defaultMessage
            )
        }
    }

    @Throws(PolicyViolationException::class)
    fun validateNameExists(name: String) {
        if (userReader.existsByName(name = name)) {
            throw PolicyViolationException(
                errorCode = ErrorCode.NAME_ALREADY_EXISTS,
                message = ErrorCode.NAME_ALREADY_EXISTS.defaultMessage
            )
        }
    }

    @Throws(PolicyViolationException::class)
    fun validatePhoneExists(phone: String) {
        if (userReader.existsByPhone(phone = phone)) {
            throw PolicyViolationException(
                errorCode = ErrorCode.PHONE_NUMBER_ALREADY_EXISTS,
                message = ErrorCode.PHONE_NUMBER_ALREADY_EXISTS.defaultMessage
            )
        }
    }

    @Throws(PolicyViolationException::class)
    fun validatePasswordMatch(rawPassword: String, encodedPassword: String) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw PolicyViolationException(
                errorCode = ErrorCode.PASSWORD_DOES_NOT_MATCHED,
                message = ErrorCode.PASSWORD_DOES_NOT_MATCHED.defaultMessage
            )
        }
    }
}
