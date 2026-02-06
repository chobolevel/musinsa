package com.musinsa.user.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.user.entity.QUser.user
import com.musinsa.user.entity.User
import com.musinsa.user.repository.UserCustomRepository
import com.musinsa.user.repository.UserRepository
import com.musinsa.user.vo.UserOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository,
    private val userCustomRepository: UserCustomRepository
) {

    fun searchUsers(
        queryFilter: UserQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserOrderType>
    ): List<User> {
        return userCustomRepository.searchUsers(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchUsersCount(queryFilter: UserQueryFilter): Long {
        return userCustomRepository.searchUsersCount(booleanExpressions = queryFilter.toBooleanExpressions())
    }

    fun findById(id: Long): User {
        return userRepository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_NOT_FOUND,
            message = ErrorCode.USER_NOT_FOUND.defaultMessage
        )
    }

    fun findByUsername(username: String): User {
        return userRepository.findByUsernameAndIsDeletedFalse(username = username) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_NOT_FOUND,
            message = ErrorCode.USER_NOT_FOUND.defaultMessage
        )
    }

    fun findBySocialId(socialId: String): User {
        return userRepository.findBySocialIdAndIsDeletedFalse(socialId = socialId) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_NOT_FOUND,
            message = ErrorCode.USER_NOT_FOUND.defaultMessage
        )
    }

    fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    fun existsBySocialId(socialId: String): Boolean {
        return userRepository.existsBySocialId(socialId)
    }

    fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    fun existsByName(name: String): Boolean {
        return userRepository.existsByName(name)
    }

    fun existsByPhone(phone: String): Boolean {
        return userRepository.existsByPhone(phone)
    }

    private fun List<UserOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map { userOrderType ->
            when (userOrderType) {
                UserOrderType.CREATED_AT_ASC -> user.createdAt.asc()
                UserOrderType.CREATED_AT_DESC -> user.createdAt.desc()
                UserOrderType.UPDATED_AT_ASC -> user.updatedAt.asc()
                UserOrderType.UPDATED_AT_DESC -> user.updatedAt.desc()
            }
        }.toTypedArray()
    }
}
