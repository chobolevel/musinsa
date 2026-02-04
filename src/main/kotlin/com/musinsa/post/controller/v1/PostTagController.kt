package com.musinsa.post.controller.v1

import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationRequest
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.post.dto.PostTagResponse
import com.musinsa.post.dto.SearchPostTagRequest
import com.musinsa.post.reader.PostTagQueryFilter
import com.musinsa.post.service.PostTagService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class PostTagController(
    private val postTagService: PostTagService
) {

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

    @GetMapping("/post-tags/{postTagId}")
    fun getPostTag(@PathVariable postTagId: Long): ResponseEntity<CommonResponse> {
        val result: PostTagResponse = postTagService.getPostTag(postTagId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
