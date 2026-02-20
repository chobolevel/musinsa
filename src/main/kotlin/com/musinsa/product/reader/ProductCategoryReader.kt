package com.musinsa.product.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.entity.QProductCategory.productCategory
import com.musinsa.product.repository.ProductCategoryCustomRepository
import com.musinsa.product.repository.ProductCategoryRepository
import com.musinsa.product.vo.ProductCategoryOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class ProductCategoryReader(
    private val productCategoryRepository: ProductCategoryRepository,
    private val productCategoryCustomRepository: ProductCategoryCustomRepository
) {

    fun searchProductCategories(
        queryFilter: ProductCategoryQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductCategoryOrderType>
    ): List<ProductCategory> {
        return productCategoryCustomRepository.searchProductCategories(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchProductCategoriesCount(queryFilter: ProductCategoryQueryFilter): Long {
        return productCategoryCustomRepository.searchProductCategoriesCount(booleanExpressions = queryFilter.toBooleanExpressions())
    }

    fun findById(id: Long): ProductCategory {
        return productCategoryRepository.findByIdAndIsDeletedFalse(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.PRODUCT_CATEGORY_NOT_FOUND,
            message = ErrorCode.PRODUCT_CATEGORY_NOT_FOUND.defaultMessage
        )
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
