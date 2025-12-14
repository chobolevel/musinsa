package com.musinsa.product.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.QProduct.product
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class ProductCustomRepository : QuerydslRepositorySupport(Product::class.java) {

    fun searchProducts(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<Product> {
        return from(product)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchProductsCount(
        booleanExpressions: Array<BooleanExpression>,
    ): Long {
        return from(product)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
