package com.musinsa.product.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.QProduct.product
import com.musinsa.product.vo.ProductOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryFacade(
    private val repository: ProductRepository,
    private val customRepository: ProductCustomRepository
) {

    fun save(product: Product): Product {
        return repository.save(product)
    }

    fun searchProducts(
        queryFilter: ProductQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductOrderType>
    ): List<Product> {
        return customRepository.searchProducts(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchProductsCount(
        queryFilter: ProductQueryFilter,
    ): Long {
        return customRepository.searchProductsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    fun findById(id: Long): Product {
        return repository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
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
