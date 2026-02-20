package com.musinsa.product.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.QProductBrand.productBrand
import com.musinsa.product.repository.ProductBrandCustomRepository
import com.musinsa.product.repository.ProductBrandRepository
import com.musinsa.product.vo.ProductBrandOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class ProductBrandReader(
    private val productBrandRepository: ProductBrandRepository,
    private val productBrandCustomRepository: ProductBrandCustomRepository
) {

    fun searchProductBrands(
        queryFilter: ProductBrandQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductBrandOrderType>
    ): List<ProductBrand> {
        return productBrandCustomRepository.searchProductBrands(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchProductBrandsCount(
        queryFilter: ProductBrandQueryFilter,
    ): Long {
        return productBrandCustomRepository.searchProductBrandsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    fun findById(id: Long): ProductBrand {
        return productBrandRepository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.PRODUCT_BRAND_NOT_FOUND,
            message = ErrorCode.PRODUCT_BRAND_NOT_FOUND.defaultMessage
        )
    }

    private fun List<ProductBrandOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                ProductBrandOrderType.YEAR_OF_LAUNCH_ASC -> productBrand.yearOfLaunch.asc()
                ProductBrandOrderType.YEAR_OF_LAUNCH_DESC -> productBrand.yearOfLaunch.desc()
                ProductBrandOrderType.CREATED_AT_ASC -> productBrand.createdAt.asc()
                ProductBrandOrderType.CREATED_AT_DESC -> productBrand.createdAt.desc()
            }
        }.toTypedArray()
    }
}
