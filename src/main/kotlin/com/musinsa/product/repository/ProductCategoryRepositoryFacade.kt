package com.musinsa.product.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.entity.QProductCategory.productCategory
import com.musinsa.product.vo.ProductCategoryOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class ProductCategoryRepositoryFacade(
    private val repository: ProductCategoryRepository,
    private val customRepository: ProductCategoryCustomRepository
) {

    fun save(productCategory: ProductCategory): ProductCategory {
        return repository.save(productCategory)
    }

    fun searchProductCategories(
        queryFilter: ProductCategoryQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductCategoryOrderType>
    ): List<ProductCategory> {
        return customRepository.searchProductCategories(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchProductCategoriesCount(queryFilter: ProductCategoryQueryFilter): Long {
        return customRepository.searchProductCategoriesCount(booleanExpressions = queryFilter.toBooleanExpressions())
    }

    private fun List<ProductCategoryOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                ProductCategoryOrderType.CREATED_AT_ASC -> productCategory.createdAt.asc()
                ProductCategoryOrderType.CREATED_AT_DESC -> productCategory.createdAt.desc()
            }
        }.toTypedArray()
    }
}
