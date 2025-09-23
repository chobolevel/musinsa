package com.musinsa.auth.strategy

import com.musinsa.auth.dto.LoginRequest
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import com.musinsa.user.vo.UserSignUpType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class SocialUserAuthenticationStrategy(
    private val repository: UserRepositoryFacade,
) : UserAuthenticationStrategy {
    override fun authenticate(request: LoginRequest): Authentication {
        val user: User = repository.findBySocialId(socialId = request.socialId!!)
        return UsernamePasswordAuthenticationToken(
            user.id,
            null,
            null
        ).also {
            it.details = user
        }
    }

    override fun supports(signUpType: UserSignUpType): Boolean {
        // 다른 소셜 로그인 지원 시 추가적인 조건 작성 요구됨
        return signUpType == UserSignUpType.KAKAO
    }
}
