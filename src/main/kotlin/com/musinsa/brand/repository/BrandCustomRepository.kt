package com.musinsa.brand.repository

import com.musinsa.brand.entity.Brand
import com.musinsa.brand.entity.QBrand.brand
import com.musinsa.common.dto.Pagination
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class BrandCustomRepository : QuerydslRepositorySupport(Brand::class.java) {

    fun searchBrands(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<Brand> {
        return from(brand)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchBrandsCount(
        booleanExpressions: Array<BooleanExpression>,
    ): Long {
        return from(brand)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
