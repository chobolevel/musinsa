package com.musinsa.common.exception

enum class ErrorCode(val defaultMessage: String) {
    INVALID_PARAMETER("파라미터가 유효하지 않습니다."),

    // AUTH
    EXPIRED_TOKEN("만료된 토큰입니다."),
    INVALID_TOKEN("유효하지 않은 토큰입니다."),

    // USER
    EMAIL_ALREADY_EXIST("이미 존재하는 이메일입니다."),
    NICKNAME_ALREADY_EXISTS("이미 존재하는 닉네임입니다."),
    USER_NOT_FOUND("회원을 찾을 수 없습니다.")
}
