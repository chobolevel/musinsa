package com.musinsa.brand.controller.v1

import com.musinsa.brand.dto.CreateBrandRequest
import com.musinsa.brand.dto.UpdateBrandRequest
import com.musinsa.brand.service.BrandService
import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
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

@RestController
@RequestMapping("/api/v1/admin")
class AdminBrandController(
    private val service: BrandService
) {

    @PostMapping("/brands")
    @HasAuthorityAdmin
    fun createBrand(
        @Valid @RequestBody
        request: CreateBrandRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createBrand(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @PutMapping("/brands/{id}")
    @HasAuthorityAdmin
    fun updateBrand(
        @PathVariable id: Long,
        @Valid @RequestBody
        request: UpdateBrandRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.updateBrand(
            id = id,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @DeleteMapping("/brands/{id}")
    @HasAuthorityAdmin
    fun deleteBrand(
        @PathVariable id: Long
    ): ResponseEntity<CommonResponse> {
        val result: Boolean = service.deleteBrand(id = id)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
