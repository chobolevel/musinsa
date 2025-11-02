package com.musinsa.brand.controller.v1

import com.musinsa.brand.repository.BrandQueryFilter
import com.musinsa.brand.service.BrandService
import com.musinsa.brand.vo.BrandOrderType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.vo.NationType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class BrandController(
    private val service: BrandService
) {

    @GetMapping("/brands")
    fun getBrands(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) englishName: String?,
        @RequestParam(required = false) nation: NationType?,
        @RequestParam(required = false) page: Long?,
        @RequestParam(required = false) size: Long?,
        @RequestParam(required = false) orderTypes: List<BrandOrderType>?
    ): ResponseEntity<PaginationResponse> {
        val queryFilter = BrandQueryFilter(
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
}
