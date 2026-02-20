package com.musinsa.product.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.QProduct.product
import com.musinsa.product.repository.ProductCustomRepository
import com.musinsa.product.repository.ProductRepository
import com.musinsa.product.vo.ProductOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class ProductReader(
    private val productRepository: ProductRepository,
    private val productCustomRepository: ProductCustomRepository
) {

    fun searchProducts(
        queryFilter: ProductQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductOrderType>
    ): List<Product> {
        return productCustomRepository.searchProducts(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchProductsCount(
        queryFilter: ProductQueryFilter,
    ): Long {
        return productCustomRepository.searchProductsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    fun findById(id: Long): Product {
        return productRepository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.PRODUCT_NOT_FOUND,
            message = ErrorCode.PRODUCT_NOT_FOUND.defaultMessage
        )
    }

    fun List<ProductOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                ProductOrderType.SORT_ORDER_ASC -> product.sortOrder.asc()
                ProductOrderType.SORT_ORDER_DESC -> product.sortOrder.desc()
                ProductOrderType.CREATED_AT_ASC -> product.sortOrder.asc()
                ProductOrderType.CREATED_AT_DESC -> product.sortOrder.desc()
            }
        }.toTypedArray()
    }
}
