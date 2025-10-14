package com.musinsa.snap.controller.v1

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.repository.SnapLikeQueryFilter
import com.musinsa.snap.service.SnapLikeService
import com.musinsa.snap.vo.SnapLikeOrderType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class SnapLikeControllerV1(
    private val service: SnapLikeService
) {

    @GetMapping("/snaps/{snapId}/likes")
    fun getSnapLikes(
        @PathVariable snapId: Long,
        @RequestParam(required = false) page: Long?,
        @RequestParam(required = false) size: Long?,
        @RequestParam(required = false) orderTypes: List<SnapLikeOrderType>?
    ): ResponseEntity<PaginationResponse> {
        val queryFilter = SnapLikeQueryFilter(
            userId = null,
            snapId = snapId
        )
        val pagination = Pagination(
            page = page ?: 1,
            size = size ?: 100
        )
        val result: PaginationResponse = service.getSnapLikes(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(result)
    }
}
