package com.musinsa.snap.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.snap.entity.QSnap.snap
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.repository.SnapCustomRepository
import com.musinsa.snap.repository.SnapRepository
import com.musinsa.snap.vo.SnapOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class SnapReader(
    private val snapRepository: SnapRepository,
    private val snapCustomRepository: SnapCustomRepository
) {

    fun searchSnaps(
        queryFilter: SnapQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapOrderType>
    ): List<Snap> {
        return snapCustomRepository.searchSnaps(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchSnapsCount(
        queryFilter: SnapQueryFilter,
    ): Long {
        return snapCustomRepository.searchSnapsCount(booleanExpressions = queryFilter.toBooleanExpressions())
    }

    fun findById(id: Long): Snap {
        return snapRepository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.SNAP_NOT_FOUND,
            message = ErrorCode.SNAP_NOT_FOUND.defaultMessage
        )
    }

    private fun List<SnapOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                SnapOrderType.CREATED_AT_ASC -> snap.createdAt.asc()
                SnapOrderType.CREATED_AT_DESC -> snap.createdAt.desc()
            }
        }.toTypedArray()
    }
}
