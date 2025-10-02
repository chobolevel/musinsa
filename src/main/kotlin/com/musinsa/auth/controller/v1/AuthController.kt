package com.musinsa.auth.controller.v1

import com.musinsa.auth.dto.JwtResponse
import com.musinsa.auth.dto.LoginRequest
import com.musinsa.auth.dto.ReissueResponse
import com.musinsa.auth.service.AuthService
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.ErrorResponse
import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
@Tag(name = "인증 API", description = "인증을 위한 API를 제공합니다.")
class AuthController(
    private val service: AuthService
) {

    @PostMapping("/login")
    @Operation(
        summary = "로그인",
        description = "회원 로그인을 통해 access token/refresh token 발급",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "로그인 성공",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = CommonResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 요청",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ErrorResponse::class)
                    )
                ]
            )
        ]
    )
    fun login(
        @Valid @RequestBody
        request: LoginRequest
    ): ResponseEntity<CommonResponse> {
        val result: JwtResponse = service.login(request = request)
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @PostMapping("/reissue")
    @Operation(
        summary = "토큰 갱신",
        description = "refresh token 통해 access token 갱신",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "토큰 발급 성공",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = CommonResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 요청",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ErrorResponse::class)
                    )
                ]
            )
        ]
    )
    fun reissue(request: HttpServletRequest): ResponseEntity<CommonResponse> {
        val refreshToken: String = request.getHeader("X-Refresh-Token") ?: throw InvalidParameterException(
            errorCode = ErrorCode.INVALID_PARAMETER,
            message = "refresh token not set on head"
        )
        val result: ReissueResponse = service.reissue(refreshToken = refreshToken)
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @PostMapping("/logout")
    @Operation(
        summary = "로그아웃",
        description = "로그아웃을 합니다.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "로그아웃 성공",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = CommonResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 요청",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ErrorResponse::class)
                    )
                ]
            )
        ]
    )
    fun logout(request: HttpServletRequest): ResponseEntity<CommonResponse> {
        val refreshToken: String = request.getHeader("X-Refresh-Token") ?: throw InvalidParameterException(
            errorCode = ErrorCode.INVALID_PARAMETER,
            message = "refresh token not set on head"
        )
        val result: Boolean = service.logout(refreshToken = refreshToken)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
