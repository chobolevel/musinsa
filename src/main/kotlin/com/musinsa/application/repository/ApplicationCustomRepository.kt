package com.musinsa.application.repository

import com.musinsa.application.entity.Application
import com.musinsa.application.entity.QApplication
import com.musinsa.common.dto.Pagination
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class ApplicationCustomRepository : QuerydslRepositorySupport(Application::class.java) {

    fun searchApplications(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<Application> {
        return from(QApplication.application)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchApplicationsCount(booleanExpressions: Array<BooleanExpression>): Long {
        return from(QApplication.application)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
