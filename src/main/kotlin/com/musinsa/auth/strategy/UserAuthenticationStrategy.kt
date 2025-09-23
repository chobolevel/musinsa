package com.musinsa.auth.strategy

import com.musinsa.auth.dto.LoginRequest
import com.musinsa.user.vo.UserSignUpType
import org.springframework.security.core.Authentication

interface UserAuthenticationStrategy {

    fun authenticate(request: LoginRequest): Authentication

    fun supports(signUpType: UserSignUpType): Boolean
}
