package com.musinsa.snap.controller.v1

import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.ErrorResponse
import com.musinsa.snap.dto.CreateSnapTagRequest
import com.musinsa.snap.service.SnapTagService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin")
@Tag(name = "관리자 스냅 태그 API", description = "관리자 스냅 태그 관리 API")
class AdminSnapTagController(
    private val service: SnapTagService
) {

    @HasAuthorityAdmin
    @PostMapping("/snap-tags")
    @Operation(
        summary = "스냅 태그 등록",
        description = "관리자 신규 스냅 태그 등록",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "스냅 태그 등록 성공",
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
    fun createSnapTag(
        @Valid @RequestBody
        request: CreateSnapTagRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createSnapTag(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }
}
