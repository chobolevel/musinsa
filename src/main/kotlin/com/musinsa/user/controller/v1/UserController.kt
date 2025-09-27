package com.musinsa.user.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.user.dto.ChangeUserPasswordRequest
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UpdateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.serivce.UserService
import com.musinsa.user.validator.UserParameterValidator
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
class UserController(
    private val service: UserService,
    private val validator: UserParameterValidator
) {

    @PostMapping("/users")
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
}
