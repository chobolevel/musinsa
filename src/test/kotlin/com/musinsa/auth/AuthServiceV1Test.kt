package com.musinsa.auth

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import com.musinsa.auth.dto.ReissueResponse
import com.musinsa.auth.service.impl.AuthServiceV1
import com.musinsa.auth.util.TokenProvider
import com.musinsa.common.properties.JwtProperties
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import kotlin.test.Test

@DisplayName("auth service v1 unit test")
@ExtendWith(MockitoExtension::class)
class AuthServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()

    @Mock
    private lateinit var userRepository: UserRepositoryFacade

    @Mock
    private lateinit var passwordEncoder: BCryptPasswordEncoder

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
        val dummyRequest = LoginRequest(
            username = "rodaka",
            password = "jik584697@",
            socialId = null,
            signUpType = UserSignUpType.GENERAL
        )
        val dummyJwtResponse = JwtResponse(
            accessToken = "accessToken",
            accessTokenExpiredAt = 0L,
            refreshToken = "refreshToken",
            refreshTokenExpiredAt = 0L
        )
        `when`(userRepository.findByUsername(username = dummyRequest.username!!)).thenReturn(dummyUser)
        `when`(passwordEncoder.matches(dummyRequest.password, dummyUser.password)).thenReturn(true)
        `when`(tokenProvider.generateToken(id = dummyUser.id!!)).thenReturn(dummyJwtResponse)

        // when
        val result: JwtResponse = service.login(request = dummyRequest)

        // then
        assertThat(result.accessToken).isEqualTo(dummyJwtResponse.accessToken)
        assertThat(result.accessTokenExpiredAt).isEqualTo(dummyJwtResponse.accessTokenExpiredAt)
        assertThat(result.refreshToken).isEqualTo(dummyJwtResponse.refreshToken)
        assertThat(result.refreshTokenExpiredAt).isEqualTo(dummyJwtResponse.refreshTokenExpiredAt)
    }

    @Test
    fun reissueTest() {
        // given
        val dummyRefreshToken = "refreshToken"
        val dummyUserId: Long = dummyUser.id!!
        val dummyJwtResponse = JwtResponse(
            accessToken = "accessToken",
            accessTokenExpiredAt = 0L,
            refreshToken = "refreshToken",
            refreshTokenExpiredAt = 0L
        )
        `when`(opsForHash.get(jwtProperties.cacheKey, dummyRefreshToken)).thenReturn(dummyUserId.toString())
        `when`(tokenProvider.getId(token = dummyRefreshToken)).thenReturn(dummyUserId)
        `when`(tokenProvider.generateToken(id = dummyUserId)).thenReturn(dummyJwtResponse)

        // when
        val result: ReissueResponse = service.reissue(refreshToken = dummyRefreshToken)

        // then
        assertThat(result.accessToken).isEqualTo(dummyJwtResponse.accessToken)
        assertThat(result.accessTokenExpiredAt).isEqualTo(dummyJwtResponse.accessTokenExpiredAt)
    }

    @Test
    fun logoutTest() {
        // given
        val dummyRefreshToken = "refreshToken"

        // when
        val result: Boolean = service.logout(refreshToken = dummyRefreshToken)

        // then
        assertThat(result).isTrue()
    }
}
