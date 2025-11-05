package com.musinsa.product.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.QProductBrand.productBrand
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class ProductBrandCustomRepository : QuerydslRepositorySupport(ProductBrand::class.java) {

    fun searchProductBrands(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<ProductBrand> {
        return from(productBrand)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchProductBrandsCount(
        booleanExpressions: Array<BooleanExpression>,
    ): Long {
        return from(productBrand)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
