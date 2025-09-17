package com.musinsa.application.entity

import com.musinsa.application.entity.QApplication.application
import com.musinsa.application.vo.ApplicationOrderType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class ApplicationRepositoryFacade(
    private val repository: ApplicationRepository,
    private val customRepository: ApplicationCustomRepository
) {

    fun save(application: Application): Application {
        return repository.save(application)
    }

    @Throws(DataNotFoundException::class)
    fun findById(id: Long): Application {
        return repository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.APPLICATION_NOT_FOUND,
            message = ErrorCode.APPLICATION_NOT_FOUND.defaultMessage
        )
    }

    @Throws(DataNotFoundException::class)
    fun findByKey(key: String): Application {
        return repository.findByKeyAndIsDeletedFalse(key = key) ?: throw DataNotFoundException(
            errorCode = ErrorCode.APPLICATION_NOT_FOUND,
            message = ErrorCode.APPLICATION_NOT_FOUND.defaultMessage
        )
    }

    fun searchApplications(
        queryFilter: ApplicationQueryFilter,
        pagination: Pagination,
        orderTypes: List<ApplicationOrderType>
    ): List<Application> {
        return customRepository.searchApplications(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchApplicationsCount(queryFilter: ApplicationQueryFilter): Long {
        return customRepository.searchApplicationsCount(booleanExpressions = queryFilter.toBooleanExpressions())
    }

    fun List<ApplicationOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                ApplicationOrderType.CREATED_AT_ASC -> application.createdAt.asc()
                ApplicationOrderType.CREATED_AT_DESC -> application.createdAt.desc()
            }
        }.toTypedArray()
    }
}
