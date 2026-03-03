package com.musinsa.auth.controller.v1

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import com.musinsa.auth.dto.ReissueResponse
import com.musinsa.auth.service.AuthService
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth(인증)", description = "인증 관리 API")
@RestController
@RequestMapping("/api/v1")
class AuthController(
    private val service: AuthService
) {

    @PostMapping("/login")
    @Operation(summary = "로그인 API")
    fun login(
        @Valid @RequestBody
        request: LoginRequest
    ): ResponseEntity<CommonResponse> {
        val result: JwtResponse = service.login(request = request)
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @PostMapping("/reissue")
    @Operation(summary = "토큰 갱신 API")
    fun reissue(request: HttpServletRequest): ResponseEntity<CommonResponse> {
        val refreshToken: String = request.getHeader("X-Refresh-Token") ?: throw InvalidParameterException(
            errorCode = ErrorCode.INVALID_PARAMETER,
            message = "refresh token not set on head"
        )
        val result: ReissueResponse = service.reissue(refreshToken = refreshToken)
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃(토큰 제거) API")
    fun logout(request: HttpServletRequest): ResponseEntity<CommonResponse> {
        val refreshToken: String = request.getHeader("X-Refresh-Token") ?: throw InvalidParameterException(
            errorCode = ErrorCode.INVALID_PARAMETER,
            message = "refresh token not set on head"
        )
        val result: Boolean = service.logout(refreshToken = refreshToken)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
