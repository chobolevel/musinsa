package com.musinsa.user.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.ErrorResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.user.dto.ChangeUserPasswordRequest
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UpdateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.serivce.UserService
import com.musinsa.user.validator.UserParameterValidator
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
@Tag(name = "회원 API", description = "일반 회원을 위한 API 제공")
class UserController(
    private val service: UserService,
    private val validator: UserParameterValidator
) {

    @PostMapping("/users")
    // swagger best practice
    @Operation(
        summary = "회원 가입",
        description = "새로운 회원을 등록",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "회원 가입 성공",
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
    fun createUser(
        @Valid @RequestBody
        request: CreateUserRequest
    ): ResponseEntity<CommonResponse> {
        validator.validate(request = request)
        val result: Long = service.createUser(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @HasAuthorityUser
    @GetMapping("/user/me")
    @Operation(
        summary = "회원 정보 조회",
        description = "회원 본인 정보 조회"
    )
    fun getMe(principal: Principal): ResponseEntity<CommonResponse> {
        val result: UserResponse = service.getUser(principal.getUserId())
        return ResponseEntity.ok().body(CommonResponse(data = result))
    }

    @HasAuthorityUser
    @PutMapping("/user/me")
    fun updateMe(
        principal: Principal,
        @Valid @RequestBody
        request: UpdateUserRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.updateUser(userId = principal.getUserId(), request = request)
        return ResponseEntity.ok().body(CommonResponse(data = result))
    }

    @HasAuthorityUser
    @DeleteMapping("/user/resign")
    fun resign(principal: Principal): ResponseEntity<CommonResponse> {
        val result: Boolean = service.resignUser(userId = principal.getUserId())
        return ResponseEntity.ok().body(CommonResponse(data = result))
    }

    @HasAuthorityUser
    @PostMapping("/user/change-password")
    fun changePassword(
        principal: Principal,
        @Valid @RequestBody
        request: ChangeUserPasswordRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.changePassword(userId = principal.getUserId(), request = request)
        return ResponseEntity.ok().body(CommonResponse(data = result))
    }

    @HasAuthorityUser
    @PostMapping("/user/following/{followingUserId}")
    fun following(principal: Principal, @PathVariable followingUserId: Long): ResponseEntity<CommonResponse> {
        val result: Boolean = service.following(
            userId = principal.getUserId(),
            followingUserId = followingUserId
        )
        return ResponseEntity.ok().body(CommonResponse(data = result))
    }
}
