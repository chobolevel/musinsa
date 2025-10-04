package com.musinsa.snap.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.snap.entity.QSnap.snap
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.vo.SnapOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class SnapRepositoryFacade(
    private val repository: SnapRepository,
    private val customRepository: SnapCustomRepository
) {

    fun save(snap: Snap): Snap {
        return repository.save(snap)
    }

    fun searchSnaps(
        queryFilter: SnapQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapOrderType>
    ): List<Snap> {
        return customRepository.searchSnaps(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchSnapsCount(
        queryFilter: SnapQueryFilter,
    ): Long {
        return customRepository.searchSnapsCount(booleanExpressions = queryFilter.toBooleanExpressions())
    }

    @Throws(DataNotFoundException::class)
    fun findById(id: Long): Snap {
        return repository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
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
