package com.musinsa.post.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.post.dto.CreatePostRequest
import com.musinsa.post.service.PostService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
class PostController(
    private val service: PostService
) {

    @PostMapping("/posts")
    @HasAuthorityUser
    fun createPost(
        principal: Principal,
        @Valid @RequestBody
        request: CreatePostRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createPost(
            userId = principal.getUserId(),
            request = request
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }
}
