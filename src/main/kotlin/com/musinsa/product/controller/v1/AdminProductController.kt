package com.musinsa.product.controller.v1

import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import com.musinsa.product.dto.CreateProductRequest
import com.musinsa.product.dto.UpdateProductRequest
import com.musinsa.product.service.ProductService
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
@RequestMapping("/api/v1")
class AdminProductController(
    private val service: ProductService
) {

    @PostMapping("/products")
    @HasAuthorityAdmin
    fun createProduct(
        @Valid @RequestBody
        request: CreateProductRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createProduct(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @PutMapping("/products/{productId}")
    @HasAuthorityAdmin
    fun updateProduct(
        @PathVariable productId: Long,
        @Valid @RequestBody
        request: UpdateProductRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.updateProduct(
            productId = productId,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @DeleteMapping("/products/{productId}")
    @HasAuthorityAdmin
    fun deleteProduct(@PathVariable productId: Long): ResponseEntity<CommonResponse> {
        val result: Boolean = service.deleteProduct(productId = productId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
