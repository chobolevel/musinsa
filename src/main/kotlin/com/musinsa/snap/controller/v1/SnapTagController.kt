package com.musinsa.snap.controller.v1

import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.dto.SnapTagResponse
import com.musinsa.snap.reader.SnapTagQueryFilter
import com.musinsa.snap.service.SnapTagService
import com.musinsa.snap.vo.SnapTagOrderType
import com.musinsa.snap.vo.SnapTagType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "SnapTag(스냅 태그)", description = "스냅 태그 관리 API")
@RestController
@RequestMapping("/api/v1")
class SnapTagController(
    private val snapTagService: SnapTagService
) {

    @Operation(summary = "스냅 태그 목록 조회 API")
    @GetMapping("/snap-tags")
    fun getSnapTags(
        @RequestParam(required = false) type: SnapTagType?,
        @RequestParam(required = false) page: Long?,
        @RequestParam(required = false) size: Long?,
        @RequestParam(required = false) orderTypes: List<SnapTagOrderType>?
    ): ResponseEntity<PaginationResponse> {
        val queryFilter = SnapTagQueryFilter(
            type = type
        )
        val pagination = Pagination(
            page = page ?: 1,
            size = size ?: 10
        )
        val result: PaginationResponse = snapTagService.getSnapTags(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(result)
    }

    @Operation(summary = "스냅 태그 단건 조회 API")
    @GetMapping("/snap-tags/{snapTagId}")
    fun getSnapTag(@PathVariable snapTagId: Long): ResponseEntity<CommonResponse> {
        val result: SnapTagResponse = snapTagService.getSnapTag(id = snapTagId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
