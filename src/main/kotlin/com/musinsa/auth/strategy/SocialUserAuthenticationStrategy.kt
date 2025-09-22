package com.musinsa.auth.strategy

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import com.musinsa.auth.util.TokenProvider
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import com.musinsa.user.vo.UserSignUpType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component

@Component
class SocialUserAuthenticationStrategy(
    private val repository: UserRepositoryFacade,
    private val tokenProvider: TokenProvider
) : UserAuthenticationStrategy {
    override fun authenticate(request: LoginRequest): JwtResponse {
        val user: User = repository.findBySocialId(socialId = request.socialId!!)
        val token = UsernamePasswordAuthenticationToken(
            user.id,
            null,
            null
        )
        return tokenProvider.generateToken(authentication = token)
    }

    override fun supports(signUpType: UserSignUpType): Boolean {
        // 다른 소셜 로그인 지원 시 추가적인 조건 작성 요구됨
        return signUpType == UserSignUpType.KAKAO
    }
}
