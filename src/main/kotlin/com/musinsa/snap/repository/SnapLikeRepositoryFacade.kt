package com.musinsa.snap.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.snap.entity.QSnapLike.snapLike
import com.musinsa.snap.entity.SnapLike
import com.musinsa.snap.vo.SnapLikeOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class SnapLikeRepositoryFacade(
    private val repository: SnapLikeRepository,
    private val customRepository: SnapLikeCustomRepository
) {

    fun searchSnapLikes(
        queryFilter: SnapLikeQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapLikeOrderType>
    ): List<SnapLike> {
        return customRepository.searchSnapLikes(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchSnapLikesCount(
        queryFilter: SnapLikeQueryFilter
    ): Long {
        return customRepository.searchSnapLikesCount(
            booleanExpressions = queryFilter.toBooleanExpressions()
        )
    }

    fun deleteById(id: Long) {
        repository.deleteById(id)
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
