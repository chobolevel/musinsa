package com.musinsa.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class CreateUserRequest(
    // TODO validation 추가 기능 확인 필요(정규식 적용 등)
    @field:Email
    val email: String,
    @field:NotEmpty
    val password: String,
    @field:NotEmpty
    val nickname: String
)
