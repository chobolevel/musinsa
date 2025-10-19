package com.musinsa.snap.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.service.SnapCommentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
class SnapCommentControllerV1(
    private val service: SnapCommentService
) {

    @PostMapping("/snaps/{snapId}/comments")
    @HasAuthorityUser
    fun createSnapComment(
        principal: Principal,
        @PathVariable snapId: Long,
        @Valid @RequestBody
        request: CreateSnapCommentRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createSnapComment(
            userId = principal.getUserId(),
            snapId = snapId,
            request = request
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }
}
