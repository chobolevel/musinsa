package com.musinsa.snap.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.snap.dto.CreateSnapRequest
import com.musinsa.snap.service.SnapService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
class SnapController(
    private val service: SnapService
) {

    @HasAuthorityUser
    @PostMapping("/snaps")
    fun createSnap(
        principal: Principal,
        @Valid @RequestBody
        request: CreateSnapRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createSnap(
            userId = principal.getUserId(),
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
