package com.musinsa.product.v1

import com.musinsa.common.dto.CommonResponse
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.vo.NationType
import com.musinsa.product.dto.ProductBrandResponse
import com.musinsa.product.repository.ProductBrandQueryFilter
import com.musinsa.product.service.ProductBrandService
import com.musinsa.product.vo.ProductBrandOrderType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ProductBrandController(
    private val service: ProductBrandService
) {

    @GetMapping("/product-brands")
    fun getProductBrands(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) englishName: String?,
        @RequestParam(required = false) nation: NationType?,
        @RequestParam(required = false) page: Long?,
        @RequestParam(required = false) size: Long?,
        @RequestParam(required = false) orderTypes: List<ProductBrandOrderType>?
    ): ResponseEntity<PaginationResponse> {
        val queryFilter = ProductBrandQueryFilter(
            name = name,
            englishName = englishName,
            nation = nation,
        )
        val pagination = Pagination(
            page = page ?: 1,
            size = size ?: 10
        )
        val result: PaginationResponse = service.getBrands(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes ?: emptyList()
        )
        return ResponseEntity.ok(result)
    }

    @GetMapping("/product-brands/{id}")
    fun getProductBrand(@PathVariable id: Long): ResponseEntity<CommonResponse> {
        val result: ProductBrandResponse = service.getBrand(id = id)
        return ResponseEntity.ok(CommonResponse(data = result))
    }
}
