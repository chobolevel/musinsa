package com.musinsa.auth.service.impl

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import com.musinsa.auth.service.AuthService
import com.musinsa.auth.strategy.UserAuthenticationProcessor
import com.musinsa.auth.util.TokenProvider
import com.musinsa.common.properties.JwtProperties
import com.musinsa.user.entity.User
import org.springframework.data.redis.core.HashOperations
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceV1(
    private val userAuthenticationProcessor: UserAuthenticationProcessor,
    private val tokenProvider: TokenProvider,
    private val opsForHash: HashOperations<String, String, String>,
    private val jwtProperties: JwtProperties
) : AuthService {

    @Transactional(readOnly = true)
    override fun login(request: LoginRequest): JwtResponse {
        val authentication: Authentication = userAuthenticationProcessor.authenticate(request = request)
        val user: User = authentication.details as User
        return tokenProvider.generateToken(authentication = authentication).also {
            opsForHash.put(jwtProperties.cacheKey, it.refreshToken, user.id.toString())
        }
    }
}
