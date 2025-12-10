package com.musinsa.product.controller.v1

import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import com.musinsa.product.dto.CreateProductCategoryRequest
import com.musinsa.product.dto.UpdateProductCategoryRequest
import com.musinsa.product.service.ProductCategoryService
import com.musinsa.product.validator.ProductCategoryParameterValidator
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
class AdminProductCategoryController(
    private val service: ProductCategoryService,
    private val validator: ProductCategoryParameterValidator
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

    @PutMapping("/product-categories/{productCategoryId}")
    @HasAuthorityAdmin
    fun updateProductCategory(
        @PathVariable("productCategoryId") productCategoryId: Long,
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

    @DeleteMapping("/product-categories/{productCategoryId}")
    @HasAuthorityAdmin
    fun deleteProductCategory(@PathVariable("productCategoryId") productCategoryId: Long): ResponseEntity<CommonResponse> {
        val result: Boolean = service.deleteProductCategory(productCategoryId = productCategoryId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
