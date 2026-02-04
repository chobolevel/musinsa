package com.musinsa.post.controller.v1

import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.service.PostTagService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin")
class AdminPostTagController(
    private val postTagService: PostTagService,
) {

    @PostMapping("/post-tags")
    @HasAuthorityAdmin
    fun createPostTag(
        @Valid @RequestBody
        request: CreatePostTagRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = postTagService.createPostTag(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }
}
