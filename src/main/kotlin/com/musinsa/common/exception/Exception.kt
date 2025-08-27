package com.musinsa.common.exception

import org.springframework.http.HttpStatus

open class MusinsaException(
    open val errorCode: ErrorCode,
    open val errorStatus: HttpStatus,
    override val message: String,
    open val throwable: Throwable? = null
) : RuntimeException(message)

open class InvalidParameterException(
    override val errorCode: ErrorCode,
    override val errorStatus: HttpStatus = HttpStatus.BAD_REQUEST,
    override val message: String,
    override val throwable: Throwable? = null
) : MusinsaException(errorCode, errorStatus, message, throwable)

open class UnauthorizedException(
    override val errorCode: ErrorCode,
    override val errorStatus: HttpStatus = HttpStatus.UNAUTHORIZED,
    override val message: String,
    override val throwable: Throwable? = null
) : MusinsaException(errorCode, errorStatus, message, throwable)

open class ForbiddenException(
    override val errorCode: ErrorCode,
    override val errorStatus: HttpStatus = HttpStatus.FORBIDDEN,
    override val message: String,
    override val throwable: Throwable? = null
) : MusinsaException(errorCode, errorStatus, message, throwable)

open class DataNotFoundException(
    override val errorCode: ErrorCode,
    override val errorStatus: HttpStatus = HttpStatus.UNAUTHORIZED,
    override val message: String,
    override val throwable: Throwable? = null
) : MusinsaException(errorCode, errorStatus, message, throwable)
