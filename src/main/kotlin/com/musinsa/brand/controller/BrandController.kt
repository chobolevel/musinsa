package com.musinsa.brand.controller

import com.musinsa.brand.dto.CreateBrandRequest
import com.musinsa.brand.service.BrandService
import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin")
class BrandController(
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
}
