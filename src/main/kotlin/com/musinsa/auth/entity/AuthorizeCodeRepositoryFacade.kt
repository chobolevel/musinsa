package com.musinsa.auth.entity

import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import org.springframework.stereotype.Repository

@Repository
class AuthorizeCodeRepositoryFacade(
    private val repository: AuthorizeCodeRepository
) {

    fun findByCode(code: String): AuthorizeCode {
        return repository.findByCode(code = code) ?: throw DataNotFoundException(
            errorCode = ErrorCode.AUTHORIZE_CODE_NOT_FOUND,
            message = ErrorCode.AUTHORIZE_CODE_NOT_FOUND.defaultMessage
        )
    }
}
