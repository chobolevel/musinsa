package com.musinsa.common.exception

enum class ErrorCode(val defaultMessage: String) {
    INVALID_PARAMETER("파라미터가 유효하지 않습니다."),
    MISSING_PARAMETER("필수 파라미터가 누락되었습니다."),
}
