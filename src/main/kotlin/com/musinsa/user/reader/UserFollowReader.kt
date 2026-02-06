package com.musinsa.user.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.user.entity.QUserFollow.userFollow
import com.musinsa.user.entity.UserFollow
import com.musinsa.user.repository.UserFollowCustomRepository
import com.musinsa.user.repository.UserFollowRepository
import com.musinsa.user.vo.UserFollowOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class UserFollowReader(
    private val userFollowRepository: UserFollowRepository,
    private val userFollowCustomRepository: UserFollowCustomRepository
) {

    fun searchUserFollows(
        queryFilter: UserFollowQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserFollowOrderType>
    ): List<UserFollow> {
        return userFollowCustomRepository.searchUserFollows(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchUserFollowsCount(queryFilter: UserFollowQueryFilter): Long {
        return userFollowCustomRepository.searchUserFollowsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    private fun List<UserFollowOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                UserFollowOrderType.CREATED_AT_ASC -> userFollow.createdAt.asc()
                UserFollowOrderType.CREATED_AT_DESC -> userFollow.createdAt.desc()
            }
        }.toTypedArray()
    }
}
