package com.musinsa.product.controller.v1

import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.dto.UpdateProductCategoryRequest
import com.musinsa.product.service.ProductCategoryService
import com.musinsa.product.validator.ProductCategoryParameterValidator
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

@Tag(name = "ProductCategory(상품 카테고리)", description = "상품 카테고리 관리 API")
@RestController
@RequestMapping("/api/v1/admin")
class AdminProductCategoryController(
    private val service: ProductCategoryService,
    private val validator: ProductCategoryParameterValidator
) {

    @Operation(summary = "상품 카테고리 등록 API")
    @PostMapping("/product-categories")
    @HasAuthorityAdmin
    fun createProductCategory(
        @Valid @RequestBody
        request: CreateProductCategoryRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createProductCategory(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @Operation(summary = "상품 카테고리 수정 API")
    @PutMapping("/product-categories/{productCategoryId}")
    @HasAuthorityAdmin
    fun updateProductCategory(
        @PathVariable productCategoryId: Long,
        @Valid @RequestBody
        request: UpdateProductCategoryRequest
    ): ResponseEntity<CommonResponse> {
        validator.validate(request = request)
        val result: Long = service.updateProductCategory(
            productCategoryId = productCategoryId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @Operation(summary = "상품 카테고리 삭제 API")
    @DeleteMapping("/product-categories/{productCategoryId}")
    @HasAuthorityAdmin
    fun deleteProductCategory(@PathVariable productCategoryId: Long): ResponseEntity<CommonResponse> {
        val result: Boolean = service.deleteProductCategory(productCategoryId = productCategoryId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
