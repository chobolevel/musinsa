package com.musinsa.post.controller.v1

import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.dto.UpdatePostTagRequest
import com.musinsa.post.service.PostTagService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    @PutMapping("/post-tags/{postTagId}")
    @HasAuthorityAdmin
    fun updatePostTag(
        @PathVariable postTagId: Long,
        @Valid @RequestBody
        request: UpdatePostTagRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = postTagService.updatePostTag(
            postTagId = postTagId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
