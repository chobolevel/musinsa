package com.musinsa.post.controller.v1

import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationRequest
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.post.dto.PostTagResponse
import com.musinsa.post.dto.SearchPostTagRequest
import com.musinsa.post.reader.PostTagQueryFilter
import com.musinsa.post.service.PostTagService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "PostTag(게시글 태그)", description = "게시글 태그 관리 API")
@RestController
@RequestMapping("/api/v1")
class PostTagController(
    private val postTagService: PostTagService
) {

    @Operation(summary = "게시글 태그 목록 조회 API")
    @GetMapping("/post-tags")
    fun getPostTags(
        searchRequest: SearchPostTagRequest,
        paginationRequest: PaginationRequest
    ): ResponseEntity<CommonResponse> {
        val result: PaginationResponse = postTagService.getPostTags(
            queryFilter = PostTagQueryFilter(
                name = searchRequest.name
            ),
            pagination = Pagination(
                page = paginationRequest.page ?: 1,
                size = paginationRequest.size ?: 20
            ),
            orderTypes = searchRequest.orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @Operation(summary = "게시글 태그 단건 조회 API")
    @GetMapping("/post-tags/{postTagId}")
    fun getPostTag(@PathVariable postTagId: Long): ResponseEntity<CommonResponse> {
        val result: PostTagResponse = postTagService.getPostTag(postTagId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
