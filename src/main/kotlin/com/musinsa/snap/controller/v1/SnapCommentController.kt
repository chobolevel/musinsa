package com.musinsa.snap.controller.v1

import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.extension.getUserId
import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.dto.SnapCommentResponse
import com.musinsa.snap.dto.UpdateSnapCommentRequest
import com.musinsa.snap.reader.SnapCommentQueryFilter
import com.musinsa.snap.service.SnapCommentService
import com.musinsa.snap.validator.SnapCommentParameterValidator
import com.musinsa.snap.vo.SnapCommentOrderType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@Tag(name = "SnapComment(스냅 댓글)", description = "스냅 댓글 관리 API")
@RestController
@RequestMapping("/api/v1")
class SnapCommentController(
    private val service: SnapCommentService,
    private val validator: SnapCommentParameterValidator
) {

    @Operation(summary = "스냅 댓글 등록 API")
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

    @Operation(summary = "스냅 댓글 목록 조회 API")
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

    @Operation(summary = "스냅 댓글 단건 조회 API")
    @GetMapping("/snaps/comments/{snapCommentId}")
    fun getSnapComment(@PathVariable snapCommentId: Long): ResponseEntity<CommonResponse> {
        val result: SnapCommentResponse = service.getSnapComment(snapCommentId = snapCommentId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @Operation(summary = "스냅 댓글 수정 API")
    @PutMapping("/snaps/comments/{snapCommentId}")
    @HasAuthorityUser
    fun updateSnapComment(
        principal: Principal,
        @PathVariable snapCommentId: Long,
        @Valid @RequestBody
        request: UpdateSnapCommentRequest
    ): ResponseEntity<CommonResponse> {
        validator.validate(request = request)
        val result: Long = service.updateSnapComment(
            userId = principal.getUserId(),
            snapCommentId = snapCommentId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @Operation(summary = "스냅 댓글 삭제 API")
    @DeleteMapping("/snaps/comments/{snapCommentId}")
    @HasAuthorityUser
    fun deleteSnapComment(
        principal: Principal,
        @PathVariable snapCommentId: Long
    ): ResponseEntity<CommonResponse> {
        val result: Boolean = service.deleteSnapComment(
            userId = principal.getUserId(),
            snapCommentId = snapCommentId
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
