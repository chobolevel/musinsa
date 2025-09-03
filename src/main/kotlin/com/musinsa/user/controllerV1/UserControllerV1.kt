package com.musinsa.user.controllerV1

import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.serivce.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
class UserControllerV1(
    private val service: UserService
) {

    @PostMapping("/users")
    fun createUser(
        @Valid @RequestBody
        request: CreateUserRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createUser(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @GetMapping("/user/me")
    fun getMe(principal: Principal): ResponseEntity<CommonResponse> {
        val result: UserResponse = service.getUser(principal.getUserId())
        return ResponseEntity.ok().body(CommonResponse(data = result))
    }
}
