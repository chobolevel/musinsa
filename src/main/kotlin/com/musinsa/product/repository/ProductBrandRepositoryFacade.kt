package com.musinsa.product.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.QProductBrand.productBrand
import com.musinsa.product.vo.ProductBrandOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class ProductBrandRepositoryFacade(
    private val repository: ProductBrandRepository,
    private val customRepository: ProductBrandCustomRepository
) {

    fun save(productBrand: ProductBrand): ProductBrand {
        return repository.save(productBrand)
    }

    fun searchProductBrands(
        queryFilter: ProductBrandQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductBrandOrderType>
    ): List<ProductBrand> {
        return customRepository.searchProductBrands(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchProductBrandsCount(
        queryFilter: ProductBrandQueryFilter,
    ): Long {
        return customRepository.searchProductBrandsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    fun findById(id: Long): ProductBrand {
        return repository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.BRAND_NOT_FOUND,
            message = ErrorCode.BRAND_NOT_FOUND.defaultMessage
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
