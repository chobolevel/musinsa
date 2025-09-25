package com.musinsa.auth.service.impl

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import com.musinsa.auth.service.AuthService
import com.musinsa.auth.util.TokenProvider
import com.musinsa.common.properties.JwtProperties
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import com.musinsa.user.vo.UserSignUpType
import org.springframework.data.redis.core.HashOperations
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceV1(
    private val userRepository: UserRepositoryFacade,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val tokenProvider: TokenProvider,
    private val opsForHash: HashOperations<String, String, String>,
    private val jwtProperties: JwtProperties
) : AuthService {

    @Transactional(readOnly = true)
    override fun login(request: LoginRequest): JwtResponse {
        val userId: Long = when (request.signUpType) {
            UserSignUpType.GENERAL -> {
                // 일반 로그인
                val user: User = userRepository.findByUsername(username = request.username!!)
                if (!passwordEncoder.matches(request.password, user.password)) {
                    throw BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.")
                }
                user.id!!
            }

            else -> {
                // 소셜 로그인
                val user: User = userRepository.findBySocialId(socialId = request.socialId!!)
                user.id!!
            }
        }
        return tokenProvider.generateToken(id = userId).also {
            opsForHash.put(jwtProperties.cacheKey, it.refreshToken, userId.toString())
        }
    }
}
