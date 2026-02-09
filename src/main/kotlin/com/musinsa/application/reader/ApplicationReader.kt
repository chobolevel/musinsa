package com.musinsa.application.reader

import com.musinsa.application.entity.Application
import com.musinsa.application.entity.QApplication
import com.musinsa.application.repository.ApplicationCustomRepository
import com.musinsa.application.repository.ApplicationRepository
import com.musinsa.application.vo.ApplicationOrderType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class ApplicationReader(
    private val applicationRepository: ApplicationRepository,
    private val applicationCustomRepository: ApplicationCustomRepository
) {

    fun findById(id: Long): Application {
        return applicationRepository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.APPLICATION_NOT_FOUND,
            message = ErrorCode.APPLICATION_NOT_FOUND.defaultMessage
        )
    }

    fun findByKey(key: String): Application {
        return applicationRepository.findByKeyAndIsDeletedFalse(key = key) ?: throw DataNotFoundException(
            errorCode = ErrorCode.APPLICATION_NOT_FOUND,
            message = ErrorCode.APPLICATION_NOT_FOUND.defaultMessage
        )
    }

    fun searchApplications(
        queryFilter: ApplicationQueryFilter,
        pagination: Pagination,
        orderTypes: List<ApplicationOrderType>
    ): List<Application> {
        return applicationCustomRepository.searchApplications(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchApplicationsCount(queryFilter: ApplicationQueryFilter): Long {
        return applicationCustomRepository.searchApplicationsCount(booleanExpressions = queryFilter.toBooleanExpressions())
    }

    fun List<ApplicationOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                ApplicationOrderType.CREATED_AT_ASC -> QApplication.application.createdAt.asc()
                ApplicationOrderType.CREATED_AT_DESC -> QApplication.application.createdAt.desc()
            }
        }.toTypedArray()
    }
}
