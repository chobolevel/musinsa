package com.musinsa.snap.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.snap.dto.CreateSnapRequest
import com.musinsa.snap.dto.SnapResponse
import com.musinsa.snap.repository.SnapQueryFilter
import com.musinsa.snap.service.SnapService
import com.musinsa.snap.vo.SnapOrderType
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
class SnapController(
    private val service: SnapService
) {

    @HasAuthorityUser
    @PostMapping("/snaps")
    fun createSnap(
        principal: Principal,
        @Valid @RequestBody
        request: CreateSnapRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createSnap(
            userId = principal.getUserId(),
            request = request
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @GetMapping("/snaps")
    fun getSnaps(
        @RequestParam(required = false) writerId: Long?,
        @RequestParam(required = false) page: Long?,
        @RequestParam(required = false) size: Long?,
        @RequestParam(required = false) orderTypes: List<SnapOrderType>?
    ): ResponseEntity<CommonResponse> {
        val queryFilter = SnapQueryFilter(
            writerId = writerId,
        )
        val pagination = Pagination(
            page = page ?: 1,
            size = size ?: 10
        )
        val result: PaginationResponse = service.getSnaps(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @GetMapping("/snaps/{snapId}")
    fun getSnap(@PathVariable("snapId") snapId: Long): ResponseEntity<CommonResponse> {
        val result: SnapResponse = service.getSnap(id = snapId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
