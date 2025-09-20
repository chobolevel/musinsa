package com.musinsa.application.controller.v1

import com.musinsa.application.dto.AddApplicationMemberRequest
import com.musinsa.application.dto.ApplicationResponse
import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.dto.UpdateApplicationRequest
import com.musinsa.application.entity.ApplicationQueryFilter
import com.musinsa.application.service.ApplicationService
import com.musinsa.application.validator.ApplicationParameterValidator
import com.musinsa.application.vo.ApplicationOrderType
import com.musinsa.common.annotation.HasAuthorityUser
import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.extension.getUserId
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

@RestController
@RequestMapping("/api/v1")
class ApplicationController(
    private val service: ApplicationService,
    private val validator: ApplicationParameterValidator
) {

    @HasAuthorityUser
    @PostMapping("/applications")
    fun createApplication(
        principal: Principal,
        @Valid @RequestBody
        request: CreateApplicationRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createApplication(
            userId = principal.getUserId(),
            request = request
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @HasAuthorityUser
    @GetMapping("/applications")
    fun getApplications(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) page: Long?,
        @RequestParam(required = false) size: Long?,
        @RequestParam(required = false) orderTypes: List<ApplicationOrderType>?
    ): ResponseEntity<CommonResponse> {
        val queryFilter = ApplicationQueryFilter(
            name = name
        )
        val pagination = Pagination(
            page = page ?: 1,
            size = size ?: 20,
        )
        val result: PaginationResponse = service.getApplications(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @HasAuthorityUser
    @GetMapping("/applications/{applicationId}")
    fun getApplication(principal: Principal, @PathVariable applicationId: Long): ResponseEntity<CommonResponse> {
        val result: ApplicationResponse = service.getApplication(
            userId = principal.getUserId(),
            applicationId = applicationId
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @HasAuthorityUser
    @PutMapping("/applications/{applicationId}")
    fun updateApplication(
        principal: Principal,
        @PathVariable applicationId: Long,
        @Valid @RequestBody
        request: UpdateApplicationRequest
    ): ResponseEntity<CommonResponse> {
        validator.validate(request = request)
        val result: Long = service.updateApplication(
            userId = principal.getUserId(),
            applicationId = applicationId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @HasAuthorityUser
    @DeleteMapping("/applications/{applicationId}")
    fun deleteApplication(
        principal: Principal,
        @PathVariable applicationId: Long
    ): ResponseEntity<CommonResponse> {
        val result: Boolean = service.deleteApplication(
            userId = principal.getUserId(),
            applicationId = applicationId
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @HasAuthorityUser
    @PostMapping("/applications/{applicationId}/members")
    fun addMember(
        principal: Principal,
        @PathVariable applicationId: Long,
        @Valid @RequestBody
        request: AddApplicationMemberRequest
    ): ResponseEntity<CommonResponse> {
        val result: Boolean = service.addMember(
            userId = principal.getUserId(),
            applicationId = applicationId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
