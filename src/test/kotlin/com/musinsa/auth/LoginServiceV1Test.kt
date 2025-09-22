package com.musinsa.auth

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import com.musinsa.auth.service.impl.AuthServiceV1
import com.musinsa.auth.strategy.UserAuthenticationProcessor
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import com.musinsa.user.vo.UserSignUpType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
@DisplayName("auth service v1 unit test")
class LoginServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()

    @Mock
    private lateinit var userAuthenticationProcessor: UserAuthenticationProcessor

    @InjectMocks
    private lateinit var service: AuthServiceV1

    @Test
    fun loginTest() {
        // given
        val request = LoginRequest(
            username = "rodaka",
            password = "jik584697@",
            socialId = null,
            signUpType = UserSignUpType.GENERAL
        )
        val jwtResponse = JwtResponse(
            accessToken = "accessToken",
            refreshToken = "refreshToken",
        )
        `when`(userAuthenticationProcessor.authenticate(request = request)).thenReturn(jwtResponse)

        // when
        val result: JwtResponse = service.login(request = request)

        // then
        assertThat(result.accessToken).isEqualTo(jwtResponse.accessToken)
        assertThat(result.refreshToken).isEqualTo(jwtResponse.refreshToken)
    }
}
