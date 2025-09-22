package com.musinsa.auth.strategy

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import com.musinsa.user.vo.UserSignUpType

interface UserAuthenticationStrategy {

    fun authenticate(request: LoginRequest): JwtResponse

    fun supports(signUpType: UserSignUpType): Boolean
}
