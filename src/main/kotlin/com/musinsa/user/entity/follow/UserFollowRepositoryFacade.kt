package com.musinsa.user.entity.follow

import com.musinsa.common.dto.Pagination
import com.musinsa.user.entity.follow.QUserFollow.userFollow
import com.musinsa.user.vo.UserFollowOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class UserFollowRepositoryFacade(
    private val repository: UserFollowRepository,
    private val customRepository: UserFollowCustomRepository
) {

    fun save(userFollow: UserFollow): UserFollow {
        return repository.save(userFollow)
    }

    fun searchUserFollows(
        queryFilter: UserFollowQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserFollowOrderType>
    ): List<UserFollow> {
        return customRepository.searchUserFollows(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchUserFollowsCount(queryFilter: UserFollowQueryFilter): Long {
        return customRepository.searchUserFollowsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    fun deleteByFollowerIdAndFollowingId(followerId: Long, followingId: Long) {
        return repository.deleteByFollowerIdAndFollowingId(followerId, followingId)
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
