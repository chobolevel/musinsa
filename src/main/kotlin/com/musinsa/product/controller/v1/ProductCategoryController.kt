package com.musinsa.product.controller.v1

import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.product.dto.ProductCategoryResponse
import com.musinsa.product.reader.ProductCategoryQueryFilter
import com.musinsa.product.service.ProductCategoryService
import com.musinsa.product.vo.ProductCategoryOrderType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ProductCategoryController(
    private val service: ProductCategoryService
) {

    @GetMapping("/product-categories")
    fun getProductCategories(
        @RequestParam(required = false) parentId: Long?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) page: Long?,
        @RequestParam(required = false) size: Long?,
        @RequestParam(required = false) orderTypes: List<ProductCategoryOrderType>?
    ): ResponseEntity<PaginationResponse> {
        val queryFilter = ProductCategoryQueryFilter(
            parentId = parentId,
            name = name,
        )
        val pagination = Pagination(
            page = page ?: 1,
            size = size ?: 20
        )
        val result: PaginationResponse = service.getProductCategories(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(result)
    }

    @GetMapping("/product-categories/{productCategoryId}")
    fun getProductCategoryById(@PathVariable productCategoryId: Long): ResponseEntity<CommonResponse> {
        val result: ProductCategoryResponse = service.getProductCategory(productCategoryId = productCategoryId)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
