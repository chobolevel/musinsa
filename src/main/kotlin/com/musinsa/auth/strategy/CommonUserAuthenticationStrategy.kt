package com.musinsa.auth.strategy

import com.musinsa.auth.dto.LoginRequest
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import com.musinsa.user.vo.UserSignUpType
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class CommonUserAuthenticationStrategy(
    private val repository: UserRepositoryFacade,
    private val passwordEncoder: BCryptPasswordEncoder,
) : UserAuthenticationStrategy {

    override fun authenticate(request: LoginRequest): Authentication {
        val user: User = repository.findByUsername(username = request.username!!)
        if (!passwordEncoder.matches(request.password, user.password)) {
            throw BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.")
        }
        return UsernamePasswordAuthenticationToken(
            user.id,
            null,
            null
        ).also {
            it.details = user
        }
    }

    override fun supports(signUpType: UserSignUpType): Boolean {
        return signUpType == UserSignUpType.GENERAL
    }
}
