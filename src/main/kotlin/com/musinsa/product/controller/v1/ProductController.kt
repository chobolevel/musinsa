package com.musinsa.product.controller.v1

import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.dto.ProductResponse
import com.musinsa.product.reader.ProductQueryFilter
import com.musinsa.product.service.ProductService
import com.musinsa.product.vo.ProductOrderType
import com.musinsa.product.vo.ProductSaleStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ProductController(
    private val service: ProductService
) {

    @GetMapping("/products")
    fun getProducts(
        @RequestParam(required = false) productBrandId: Long?,
        @RequestParam(required = false) productCategoryId: Long?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) saleStatus: ProductSaleStatus?,
        @RequestParam(required = false) page: Long?,
        @RequestParam(required = false) size: Long?,
        @RequestParam(required = false) orderTypes: List<ProductOrderType>?
    ): ResponseEntity<PaginationResponse> {
        val queryFilter = ProductQueryFilter(
            productBrandId = productBrandId,
            productCategoryId = productCategoryId,
            name = name,
            saleStatus = saleStatus,
        )
        val pagination = Pagination(
            page = page ?: 1,
            size = size ?: 20
        )
        val result: PaginationResponse = service.getProducts(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(result)
    }

    @GetMapping("/products/{productId}")
    fun getProduct(@PathVariable productId: Long): ResponseEntity<CommonResponse> {
        val result: ProductResponse = service.getProduct(productId = productId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
