package com.musinsa.snap.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.snap.entity.QSnapTag.snapTag
import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.repository.SnapTagCustomRepository
import com.musinsa.snap.repository.SnapTagRepository
import com.musinsa.snap.vo.SnapTagOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class SnapTagReader(
    private val snapTagRepository: SnapTagRepository,
    private val snapTagCustomRepository: SnapTagCustomRepository
) {

    fun searchSnapTags(
        queryFilter: SnapTagQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapTagOrderType>
    ): List<SnapTag> {
        return snapTagCustomRepository.searchSnapTags(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchSnapTagsCount(queryFilter: SnapTagQueryFilter): Long {
        return snapTagCustomRepository.searchSnapTagsCount(booleanExpressions = queryFilter.toBooleanExpressions())
    }

    fun findByIds(ids: List<Long>): List<SnapTag> {
        return snapTagRepository.findByIdInAndIsDeletedFalse(ids = ids)
    }

    fun findById(id: Long): SnapTag {
        return snapTagRepository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.SNAP_TAG_NOT_FOUND,
            message = ErrorCode.SNAP_TAG_NOT_FOUND.defaultMessage
        )
    }

    private fun List<SnapTagOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                SnapTagOrderType.CREATED_AT_ASC -> snapTag.createdAt.asc()
                SnapTagOrderType.CREATED_AT_DESC -> snapTag.createdAt.desc()
            }
        }.toTypedArray()
    }
}
