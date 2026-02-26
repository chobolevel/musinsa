package com.musinsa.post.controller.v1

import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.dto.UpdatePostTagRequest
import com.musinsa.post.service.PostTagService
import com.musinsa.post.validator.PostTagParameterValidator
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "PostTag(게시글 태그)", description = "게시글 태그 관리 API")
@RestController
@RequestMapping("/api/v1/admin")
class AdminPostTagController(
    private val postTagService: PostTagService,
    private val postTagParameterValidator: PostTagParameterValidator,
) {

    @Operation(summary = "게시글 태그 등록 API")
    @PostMapping("/post-tags")
    @HasAuthorityAdmin
    fun createPostTag(
        @Valid @RequestBody
        request: CreatePostTagRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = postTagService.createPostTag(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @Operation(summary = "게시글 태그 수정 API")
    @PutMapping("/post-tags/{postTagId}")
    @HasAuthorityAdmin
    fun updatePostTag(
        @PathVariable postTagId: Long,
        @Valid @RequestBody
        request: UpdatePostTagRequest
    ): ResponseEntity<CommonResponse> {
        postTagParameterValidator.validate(request = request)
        val result: Long = postTagService.updatePostTag(
            postTagId = postTagId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @Operation(summary = "게시글 태그 삭제 API")
    @DeleteMapping("/post-tags/{postTagId}")
    @HasAuthorityAdmin
    fun deletePostTag(@PathVariable postTagId: Long): ResponseEntity<CommonResponse> {
        val result: Boolean = postTagService.deletePostTag(postTagId = postTagId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
