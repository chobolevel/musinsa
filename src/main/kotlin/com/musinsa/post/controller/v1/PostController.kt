package com.musinsa.post.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.dto.PaginationRequest
import com.musinsa.common.extension.getUserId
import com.musinsa.post.dto.CreatePostRequest
import com.musinsa.post.dto.PostSearchRequest
import com.musinsa.post.reader.PostQueryFilter
import com.musinsa.post.service.PostService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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

    @GetMapping("/posts")
    fun getPosts(
        request: PostSearchRequest,
        paginationRequest: PaginationRequest,
    ): ResponseEntity<CommonResponse> {
        val result: PaginationResponse = service.getPosts(
            queryFilter = PostQueryFilter(
                userId = request.userId,
                keyword = request.keyword,
            ),
            pagination = Pagination(
                page = paginationRequest.page ?: 1,
                size = paginationRequest.size ?: 20
            ),
            orderTypes = request.orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
