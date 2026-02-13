package com.musinsa.post.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.post.dto.CreatePostCommentRequest
import com.musinsa.post.service.PostCommentService
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
class PostCommentController(
    private val postCommentService: PostCommentService
) {

    @PostMapping("/posts/{postId}/comments")
    @HasAuthorityUser
    fun createPostComment(
        principal: Principal,
        @PathVariable postId: Long,
        @Valid @RequestBody
        request: CreatePostCommentRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = postCommentService.createPostComment(
            userId = principal.getUserId(),
            postId = postId,
            request = request
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }
}
