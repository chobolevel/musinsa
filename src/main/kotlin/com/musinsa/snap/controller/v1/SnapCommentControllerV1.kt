package com.musinsa.snap.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.dto.SnapCommentResponse
import com.musinsa.snap.dto.UpdateSnapCommentRequest
import com.musinsa.snap.repository.SnapCommentQueryFilter
import com.musinsa.snap.service.SnapCommentService
import com.musinsa.snap.validator.SnapCommentParameterValidator
import com.musinsa.snap.vo.SnapCommentOrderType
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
class SnapCommentControllerV1(
    private val service: SnapCommentService,
    private val validator: SnapCommentParameterValidator
) {

    @PostMapping("/snaps/{snapId}/comments")
    @HasAuthorityUser
    fun createSnapComment(
        principal: Principal,
        @PathVariable snapId: Long,
        @Valid @RequestBody
        request: CreateSnapCommentRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createSnapComment(
            userId = principal.getUserId(),
            snapId = snapId,
            request = request
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @GetMapping("/snaps/comments")
    fun getSnapComments(
        @RequestParam(required = false) snapId: Long?,
        @RequestParam(required = false) userId: Long?,
        @RequestParam(required = false) page: Long?,
        @RequestParam(required = false) size: Long?,
        @RequestParam(required = false) orderTypes: List<SnapCommentOrderType>?
    ): ResponseEntity<PaginationResponse> {
        val queryFilter = SnapCommentQueryFilter(
            snapId = snapId,
            userId = userId,
        )
        val pagination = Pagination(
            page = page ?: 1,
            size = size ?: 20
        )
        val result: PaginationResponse = service.getSnapComments(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(result)
    }

    @GetMapping("/snaps/comments/{snapCommentId}")
    fun getSnapComment(@PathVariable snapCommentId: Long): ResponseEntity<CommonResponse> {
        val result: SnapCommentResponse = service.getSnapComment(snapCommentId = snapCommentId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @PutMapping("/snaps/comments/{snapCommentId}")
    @HasAuthorityUser
    fun updateSnapComment(
        principal: Principal,
        @PathVariable snapCommentId: Long,
        @Valid @RequestBody
        request: UpdateSnapCommentRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.updateSnapComment(
            userId = principal.getUserId(),
            snapCommentId = snapCommentId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
