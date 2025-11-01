package com.musinsa.brand.repository

import com.musinsa.brand.entity.Brand
import com.musinsa.brand.entity.QBrand.brand
import com.musinsa.brand.vo.BrandOrderType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class BrandRepositoryFacade(
    private val repository: BrandRepository,
    private val customRepository: BrandCustomRepository
) {

    fun save(brand: Brand): Brand {
        return repository.save(brand)
    }

    fun searchBrands(
        queryFilter: BrandQueryFilter,
        pagination: Pagination,
        orderTypes: List<BrandOrderType>
    ): List<Brand> {
        return customRepository.searchBrands(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchBrandsCount(
        queryFilter: BrandQueryFilter,
    ): Long {
        return customRepository.searchBrandsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    fun findById(id: Long): Brand {
        return repository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.BRAND_NOT_FOUND,
            message = ErrorCode.BRAND_NOT_FOUND.defaultMessage
        )
    }

    private fun List<BrandOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                BrandOrderType.YEAR_OF_LAUNCH_ASC -> brand.yearOfLaunch.asc()
                BrandOrderType.YEAR_OF_LAUNCH_DESC -> brand.yearOfLaunch.desc()
                BrandOrderType.CREATED_AT_ASC -> brand.createdAt.asc()
                BrandOrderType.CREATED_AT_DESC -> brand.createdAt.desc()
            }
        }.toTypedArray()
    }
}
