package com.musinsa.user.entity

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.extension.toOrderSpecifiers
import com.musinsa.user.vo.UserOrderType
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
        return repository.findByIdAndResignedFalse(id = id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_NOT_FOUND,
            message = ErrorCode.USER_NOT_FOUND.defaultMessage
        )
    }

    fun existsByEmail(email: String): Boolean {
        return repository.existsByEmail(email = email)
    }

    fun existsByNickname(nickname: String): Boolean {
        return repository.existsByNickname(nickname = nickname)
    }
}
