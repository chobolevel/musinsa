package com.musinsa.product.controller.v1

import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.service.ProductCategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin")
class AdminProductCategoryController(
    private val service: ProductCategoryService
) {

    @PostMapping("/product-categories")
    @HasAuthorityAdmin
    fun createProductCategory(
        @Valid @RequestBody
        request: CreateProductCategoryRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createProductCategory(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }
}
