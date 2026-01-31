package com.musinsa.post.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationRequest
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.post.dto.CreatePostRequest
import com.musinsa.post.dto.PostResponse
import com.musinsa.post.dto.PostSearchRequest
import com.musinsa.post.dto.UpdatePostRequest
import com.musinsa.post.reader.PostQueryFilter
import com.musinsa.post.service.PostService
import com.musinsa.post.validator.PostParameterValidator
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
class PostController(
    private val service: PostService,
    private val postParameterValidator: PostParameterValidator,
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

    // 요청 DTO는 IS A 관계가 아니므로 상속보다는 합성(composition)이 적합
    // 의미 관계 명확하고 확장/유지보수 측면에서도 안전
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

    @GetMapping("/posts/{postId}")
    fun getPost(@PathVariable postId: Long): ResponseEntity<CommonResponse> {
        val result: PostResponse = service.getPost(postId = postId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @PutMapping("/posts/{postId}")
    @HasAuthorityUser
    fun updatePost(
        principal: Principal,
        @PathVariable postId: Long,
        @Valid @RequestBody
        request: UpdatePostRequest
    ): ResponseEntity<CommonResponse> {
        postParameterValidator.validate(request = request)
        val result: Long = service.updatePost(
            userId = principal.getUserId(),
            postId = postId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
