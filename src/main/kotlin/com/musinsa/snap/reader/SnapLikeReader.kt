package com.musinsa.snap.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.snap.entity.QSnapLike.snapLike
import com.musinsa.snap.entity.SnapLike
import com.musinsa.snap.repository.SnapLikeCustomRepository
import com.musinsa.snap.repository.SnapLikeRepository
import com.musinsa.snap.vo.SnapLikeOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class SnapLikeReader(
    private val snapLikeRepository: SnapLikeRepository,
    private val snapLikeCustomRepository: SnapLikeCustomRepository
) {

    fun searchSnapLikes(
        queryFilter: SnapLikeQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapLikeOrderType>
    ): List<SnapLike> {
        return snapLikeCustomRepository.searchSnapLikes(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchSnapLikesCount(
        queryFilter: SnapLikeQueryFilter
    ): Long {
        return snapLikeCustomRepository.searchSnapLikesCount(
            booleanExpressions = queryFilter.toBooleanExpressions()
        )
    }

    fun existsBySnapIdAndUserId(snapId: Long, userId: Long): Boolean {
        return snapLikeRepository.existsBySnapIdAndUserId(snapId = snapId, userId = userId)
    }

    private fun List<SnapLikeOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                SnapLikeOrderType.CREATED_AT_ASC -> snapLike.createdAt.asc()
                SnapLikeOrderType.CREATED_AT_DESC -> snapLike.createdAt.desc()
            }
        }.toTypedArray()
    }
}
