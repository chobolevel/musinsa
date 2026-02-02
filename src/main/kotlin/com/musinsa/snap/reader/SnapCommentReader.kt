package com.musinsa.snap.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.snap.entity.QSnapComment.snapComment
import com.musinsa.snap.entity.SnapComment
import com.musinsa.snap.repository.SnapCommentCustomRepository
import com.musinsa.snap.repository.SnapCommentRepository
import com.musinsa.snap.vo.SnapCommentOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class SnapCommentReader(
    private val snapCommentRepository: SnapCommentRepository,
    private val snapCommentCustomRepository: SnapCommentCustomRepository
) {
    fun searchSnapComments(
        queryFilter: SnapCommentQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapCommentOrderType>
    ): List<SnapComment> {
        return snapCommentCustomRepository.searchSnapComments(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchSnapCommentsCount(
        queryFilter: SnapCommentQueryFilter,
    ): Long {
        return snapCommentCustomRepository.searchSnapCommentsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    fun findById(id: Long): SnapComment {
        return snapCommentRepository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.SNAP_COMMENT_NOT_FOUND,
            message = ErrorCode.SNAP_COMMENT_NOT_FOUND.defaultMessage
        )
    }

    private fun List<SnapCommentOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                SnapCommentOrderType.CREATED_AT_ASC -> snapComment.createdAt.asc()
                SnapCommentOrderType.CREATED_AT_DESC -> snapComment.createdAt.desc()
            }
        }.toTypedArray()
    }
}
