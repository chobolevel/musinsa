package com.musinsa.post.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationRequest
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.post.dto.CreatePostCommentRequest
import com.musinsa.post.dto.PostCommentResponse
import com.musinsa.post.dto.SearchPostCommentRequest
import com.musinsa.post.dto.UpdatePostCommentRequest
import com.musinsa.post.reader.PostCommentQueryFilter
import com.musinsa.post.service.PostCommentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
class PostCommentController(
    private val postCommentService: PostCommentService,
    commentService: PostCommentService
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

    @GetMapping("/posts/comments")
    fun getPostComments(
        request: SearchPostCommentRequest,
        paginationRequest: PaginationRequest
    ): ResponseEntity<CommonResponse> {
        val queryFilter = PostCommentQueryFilter(
            userId = request.userId,
            postId = request.postId,
            parentId = request.parentId,
        )
        val pagination = Pagination(
            page = paginationRequest.page ?: 1,
            size = paginationRequest.size ?: 10
        )
        val result: PaginationResponse = postCommentService.getPostComments(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = request.orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @GetMapping("/posts/comments/{postCommentId}")
    fun getPostComment(@PathVariable postCommentId: Long): ResponseEntity<CommonResponse> {
        val result: PostCommentResponse = postCommentService.getPostComment(postCommentId = postCommentId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @PutMapping("/posts/comments/{postCommentId}")
    @HasAuthorityUser
    fun updatePostComment(
        principal: Principal,
        @PathVariable postCommentId: Long,
        @Valid @RequestBody
        request: UpdatePostCommentRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = postCommentService.updatePostComment(
            userId = principal.getUserId(),
            postCommentId = postCommentId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
