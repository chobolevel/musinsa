package com.musinsa.snap.entity

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.snap.entity.QSnapTag.snapTag
import com.musinsa.snap.vo.SnapTagOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class SnapTagRepositoryFacade(
    private val repository: SnapTagRepository,
    private val customRepository: SnapTagCustomRepository
) {

    fun save(snapTag: SnapTag): SnapTag {
        return repository.save(snapTag)
    }

    fun searchSnapTags(
        queryFilter: SnapTagQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapTagOrderType>
    ): List<SnapTag> {
        return customRepository.searchSnapTags(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchSnapTagsCount(queryFilter: SnapTagQueryFilter): Long {
        return customRepository.searchSnapTagsCount(booleanExpressions = queryFilter.toBooleanExpressions())
    }

    @Throws(DataNotFoundException::class)
    fun findById(id: Long): SnapTag {
        return repository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
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
