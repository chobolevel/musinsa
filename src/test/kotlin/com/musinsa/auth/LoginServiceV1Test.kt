package com.musinsa.auth

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import com.musinsa.auth.service.impl.AuthServiceV1
import com.musinsa.auth.strategy.UserAuthenticationProcessor
import com.musinsa.auth.util.TokenProvider
import com.musinsa.common.properties.JwtProperties
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import com.musinsa.user.vo.UserSignUpType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.redis.core.HashOperations
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
@DisplayName("auth service v1 unit test")
class LoginServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()

    @Mock
    private lateinit var userAuthenticationProcessor: UserAuthenticationProcessor

    @Mock
    private lateinit var tokenProvider: TokenProvider

    @Mock
    private lateinit var opsForHash: HashOperations<String, String, String>

    @Mock
    private lateinit var jwtProperties: JwtProperties

    @InjectMocks
    private lateinit var service: AuthServiceV1

    @BeforeEach
    fun init() {
        `when`(jwtProperties.cacheKey).thenReturn("user-refresh-token:v1")
    }

    @Test
    fun loginTest() {
        // given
        val request = LoginRequest(
            username = "rodaka",
            password = "jik584697@",
            socialId = null,
            signUpType = UserSignUpType.GENERAL
        )
        val dummyToken = UsernamePasswordAuthenticationToken(
            dummyUser.id.toString(),
            null,
            null
        ).also {
            it.details = dummyUser
        }
        val dummyJwtResponse = JwtResponse(
            accessToken = "accessToken",
            refreshToken = "refreshToken",
        )
        `when`(userAuthenticationProcessor.authenticate(request = request)).thenReturn(dummyToken)
        `when`(tokenProvider.generateToken(authentication = dummyToken)).thenReturn(dummyJwtResponse)

        // when
        val result: JwtResponse = service.login(request = request)

        // then
        assertThat(result.accessToken).isEqualTo(dummyJwtResponse.accessToken)
        assertThat(result.refreshToken).isEqualTo(dummyJwtResponse.refreshToken)
    }
}
