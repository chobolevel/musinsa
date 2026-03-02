package com.musinsa.product.controller.v1

import com.musinsa.common.annotation.HasAuthorityAdmin
import com.musinsa.common.dto.CommonResponse
import com.musinsa.product.dto.CreateProductBrandRequest
import com.musinsa.product.dto.UpdateProductBrandRequest
import com.musinsa.product.service.ProductBrandService
import com.musinsa.product.validator.ProductBrandParameterValidator
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

@Tag(name = "ProductBrand(상품 브랜드)", description = "상품 브랜드 관리 API")
@RestController
@RequestMapping("/api/v1/admin")
class AdminProductBrandController(
    private val service: ProductBrandService,
    private val validator: ProductBrandParameterValidator
) {

    @Operation(summary = "상품 브랜드 등록 API")
    @PostMapping("/product-brands")
    @HasAuthorityAdmin
    fun createProductBrand(
        @Valid @RequestBody
        request: CreateProductBrandRequest
    ): ResponseEntity<CommonResponse> {
        val result: Long = service.createBrand(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse(data = result))
    }

    @Operation(summary = "상품 브랜드 수정 API")
    @PutMapping("/product-brands/{id}")
    @HasAuthorityAdmin
    fun updateProductBrand(
        @PathVariable id: Long,
        @Valid @RequestBody
        request: UpdateProductBrandRequest
    ): ResponseEntity<CommonResponse> {
        validator.validate(request = request)
        val result: Long = service.updateBrand(
            id = id,
            request = request
        )
        return ResponseEntity.ok(CommonResponse(data = result))
    }

    @Operation(summary = "상품 브랜드 삭제 API")
    @DeleteMapping("/product-brands/{id}")
    @HasAuthorityAdmin
    fun deleteProductBrand(
        @PathVariable id: Long
    ): ResponseEntity<CommonResponse> {
        val result: Boolean = service.deleteBrand(id = id)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
