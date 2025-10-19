package com.musinsa.snap.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.snap.entity.QSnapComment.snapComment
import com.musinsa.snap.entity.SnapComment
import com.musinsa.snap.vo.SnapCommentOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class SnapCommentRepositoryFacade(
    private val repository: SnapCommentRepository,
    private val customRepository: SnapCommentCustomRepository
) {

    fun save(snapComment: SnapComment): SnapComment {
        return repository.save(snapComment)
    }

    fun searchSnapComments(
        queryFilter: SnapCommentQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapCommentOrderType>
    ): List<SnapComment> {
        return customRepository.searchSnapComments(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchSnapCommentsCount(
        queryFilter: SnapCommentQueryFilter,
    ): Long {
        return customRepository.searchSnapCommentsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    @Throws(DataNotFoundException::class)
    fun findById(id: Long): SnapComment {
        return repository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
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
