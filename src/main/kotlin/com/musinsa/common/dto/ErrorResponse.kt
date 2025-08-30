package com.musinsa.common.dto

import com.musinsa.common.exception.ErrorCode

data class ErrorResponse(
    val errorCode: ErrorCode,
    val errorMessage: String,
)
