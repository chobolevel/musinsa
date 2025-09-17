package com.musinsa.application.controller.v1

import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.service.ApplicationService
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.extension.getUserId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
class ApplicationController(
    private val service: ApplicationService
) {

    @PostMapping("/applications")
    fun createApplication(
        principal: Principal,
        @Valid @RequestBody
        request: CreateApplicationRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createApplication(
            userId = principal.getUserId(),
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
