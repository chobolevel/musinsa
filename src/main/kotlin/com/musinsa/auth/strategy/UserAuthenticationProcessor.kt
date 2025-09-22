package com.musinsa.auth.strategy

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Component

@Component
class UserAuthenticationProcessor(
    private val strategies: List<UserAuthenticationStrategy>
) {

    fun authenticate(request: LoginRequest): JwtResponse {
        return strategies
            .find { it.supports(signUpType = request.signUpType) }
            ?.authenticate(request = request)
            ?: throw BadCredentialsException("로그인 정보가 올바르지 않습니다.")
    }
}
