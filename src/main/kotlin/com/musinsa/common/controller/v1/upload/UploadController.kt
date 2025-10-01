package com.musinsa.common.controller.v1.upload

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.ErrorResponse
import com.musinsa.common.dto.UploadRequestDto
import com.musinsa.common.service.upload.UploadService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
@Tag(name = "파일 업로드 API", description = "파일 업로드를 위한 API 제공")
class UploadController(
    private val service: UploadService
) {

    @HasAuthorityUser
    @PostMapping("/upload/presigned-url")
    @Operation(
        summary = "파일 업로드 위한 Presigned Url 발급",
        description = "새로운 파일을 업로드를 위한 AWS S3 Presigned Url 발급",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "Presigned Url 발급 성공",
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
    fun issuePresignedUrl(@RequestBody request: UploadRequestDto): ResponseEntity<CommonResponse> {
        val result = service.getPresignedUrl(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(result))
    }
}
