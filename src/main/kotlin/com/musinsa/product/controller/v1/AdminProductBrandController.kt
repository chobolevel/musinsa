package com.musinsa.product.controller.v1

import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import com.musinsa.product.dto.CreateProductBrandRequest
import com.musinsa.product.dto.UpdateProductBrandRequest
import com.musinsa.product.service.ProductBrandService
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
class AdminProductBrandController(
    private val service: ProductBrandService
) {

    @PostMapping("/product-brands")
    @HasAuthorityAdmin
    fun createProductBrand(
        @Valid @RequestBody
        request: CreateProductBrandRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createBrand(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @PutMapping("/product-brands/{id}")
    @HasAuthorityAdmin
    fun updateProductBrand(
        @PathVariable id: Long,
        @Valid @RequestBody
        request: UpdateProductBrandRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.updateBrand(
            id = id,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @DeleteMapping("/product-brands/{id}")
    @HasAuthorityAdmin
    fun deleteProductBrand(
        @PathVariable id: Long
    ): ResponseEntity<CommonResponse> {
        val result: Boolean = service.deleteBrand(id = id)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
