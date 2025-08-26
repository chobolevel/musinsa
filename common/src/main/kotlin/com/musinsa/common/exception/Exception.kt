package com.musinsa.common.exception

open class MusinsaException(
    open val errorCode: ErrorCode,
    open val errorStatus: ErrorStatus,
    override val message: String,
    open val throwable: Throwable? = null
) : RuntimeException(message)

data class ParameterInvalidException(
    override val errorCode: ErrorCode,
    override val errorStatus: ErrorStatus = ErrorStatus.BAD_REQUEST,
    override val message: String,
    override val throwable: Throwable? = null
) : MusinsaException(errorCode, errorStatus, message, throwable)

data class PolicyException(
    override val errorCode: ErrorCode,
    override val errorStatus: ErrorStatus = ErrorStatus.BAD_REQUEST,
    override val message: String,
    override val throwable: Throwable? = null
) : MusinsaException(errorCode, errorStatus, message, throwable)

data class UnauthorizedException(
    override val errorCode: ErrorCode,
    override val errorStatus: ErrorStatus = ErrorStatus.UNAUTHORIZED,
    override val message: String,
    override val throwable: Throwable? = null
) : MusinsaException(errorCode, errorStatus, message, throwable)

data class ForbiddenException(
    override val errorCode: ErrorCode,
    override val errorStatus: ErrorStatus = ErrorStatus.FORBIDDEN,
    override val message: String,
    override val throwable: Throwable? = null
) : MusinsaException(errorCode, errorStatus, message, throwable)

data class DataNotFoundException(
    override val errorCode: ErrorCode,
    override val errorStatus: ErrorStatus = ErrorStatus.NOT_FOUND,
    override val message: String,
    override val throwable: Throwable? = null
) : MusinsaException(errorCode, errorStatus, message, throwable)
