package com.musinsa.snap.controller.v1

import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import com.musinsa.snap.dto.CreateSnapTagRequest
import com.musinsa.snap.dto.UpdateSnapTagRequest
import com.musinsa.snap.service.SnapTagService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "SnapTag(스냅 태그)", description = "스냅 태그 관리 API")
@RestController
@RequestMapping("/api/v1/admin")
class AdminSnapTagController(
    private val service: SnapTagService
) {

    @HasAuthorityAdmin
    @PostMapping("/snap-tags")
    @Operation(summary = "스냅 태그 등록 API")
    fun createSnapTag(
        @Valid @RequestBody
        request: CreateSnapTagRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createSnapTag(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @HasAuthorityAdmin
    @PutMapping("/snap-tags/{snapTagId}")
    fun updateSnapTag(
        @PathVariable snapTagId: Long,
        @Valid @RequestBody
        request: UpdateSnapTagRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.updateSnapTag(
            snapTagId = snapTagId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @HasAuthorityAdmin
    @DeleteMapping("/snap-tags/{snapTagId}")
    fun deleteSnapTag(@PathVariable snapTagId: Long): ResponseEntity<CommonResponse> {
        val result: Boolean = service.deleteSnapTag(snapTagId = snapTagId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
