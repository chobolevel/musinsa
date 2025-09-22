package com.musinsa.auth.dto

import com.musinsa.user.vo.UserSignUpType
import jakarta.validation.constraints.NotNull

data class LoginRequest(
    val username: String?,
    val password: String?,
    val socialId: String?,
    @field:NotNull
    val signUpType: UserSignUpType
)
