package com.musinsa.user.entity

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.user.entity.QUser.user
import com.musinsa.user.vo.UserOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class UserRepositoryFacade(
    private val repository: UserRepository,
    private val customRepository: UserCustomRepository
) {

    fun save(user: User): User {
        return repository.save(user)
    }

    fun searchUsers(
        queryFilter: UserQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserOrderType>
    ): List<User> {
        return customRepository.searchUsers(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchUsersCount(queryFilter: UserQueryFilter): Long {
        return customRepository.searchUsersCount(booleanExpressions = queryFilter.toBooleanExpressions())
    }

    @Throws(DataNotFoundException::class)
    fun findById(id: Long): User {
        return repository.findByIdAndIsDeletedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_NOT_FOUND,
            message = ErrorCode.USER_NOT_FOUND.defaultMessage
        )
    }

    @Throws(DataNotFoundException::class)
    fun findByUsername(username: String): User {
        return repository.findByUsernameAndIsDeletedFalse(username = username) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_NOT_FOUND,
            message = ErrorCode.USER_NOT_FOUND.defaultMessage
        )
    }

    @Throws(DataNotFoundException::class)
    fun findBySocialId(socialId: String): User {
        return repository.findBySocialIdAndIsDeletedFalse(socialId = socialId) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_NOT_FOUND,
            message = ErrorCode.USER_NOT_FOUND.defaultMessage
        )
    }

    fun existsByUsername(username: String): Boolean {
        return repository.existsByUsername(username)
    }

    fun existsBySocialId(socialId: String): Boolean {
        return repository.existsBySocialId(socialId)
    }

    fun existsByEmail(email: String): Boolean {
        return repository.existsByEmail(email)
    }

    fun existsByName(name: String): Boolean {
        return repository.existsByName(name)
    }

    fun existsByPhone(phone: String): Boolean {
        return repository.existsByPhone(phone)
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
