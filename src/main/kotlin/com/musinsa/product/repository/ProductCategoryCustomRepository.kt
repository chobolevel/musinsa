package com.musinsa.product.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.entity.QProductCategory.productCategory
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class ProductCategoryCustomRepository : QuerydslRepositorySupport(ProductCategory::class.java) {

    fun searchProductCategories(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<ProductCategory> {
        return from(productCategory)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchProductCategoriesCount(
        booleanExpressions: Array<BooleanExpression>,
    ): Long {
        return from(productCategory)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
