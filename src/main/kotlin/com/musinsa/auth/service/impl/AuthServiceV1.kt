package com.musinsa.auth.service.impl

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import com.musinsa.auth.service.AuthService
import com.musinsa.auth.strategy.UserAuthenticationProcessor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceV1(
    private val userAuthenticationProcessor: UserAuthenticationProcessor,
) : AuthService {

    @Transactional(readOnly = true)
    override fun login(request: LoginRequest): JwtResponse {
        return userAuthenticationProcessor.authenticate(request = request)
    }
}
