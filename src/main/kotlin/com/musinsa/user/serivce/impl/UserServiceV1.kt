package com.musinsa.user.serivce.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.user.converter.UserConverter
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserOrderType
import com.musinsa.user.entity.UserQueryFilter
import com.musinsa.user.entity.UserRepositoryFacade
import com.musinsa.user.serivce.UserService
import com.musinsa.user.validator.UserBusinessValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceV1(
    private val repository: UserRepositoryFacade,
    private val converter: UserConverter,
    private val validator: UserBusinessValidator
) : UserService {

    @Transactional
    override fun createUser(request: CreateUserRequest): Long {
        validator.validateEmailExist(email = request.email)
        validator.validateNicknameExist(nickname = request.nickname)
        val user: User = converter.toEntity(request = request)
        return repository.save(user = user).id!!
    }

    override fun getUsers(
        queryFilter: UserQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserOrderType>
    ): PaginationResponse {
        val users: List<User> = repository.searchUsers(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = repository.searchUsersCount(queryFilter = queryFilter)
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(users = users),
            totalCount = totalCount
        )
    }

    override fun getUser(id: Long): UserResponse {
        val user: User = repository.findById(id = id)
        return converter.toResponse(user = user)
    }
}
