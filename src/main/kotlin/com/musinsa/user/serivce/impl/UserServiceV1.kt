package com.musinsa.user.serivce.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.PolicyViolationException
import com.musinsa.user.converter.UserConverter
import com.musinsa.user.dto.ChangeUserPasswordRequest
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UpdateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserQueryFilter
import com.musinsa.user.entity.UserRepositoryFacade
import com.musinsa.user.serivce.UserService
import com.musinsa.user.updater.UserUpdater
import com.musinsa.user.validator.UserBusinessValidator
import com.musinsa.user.vo.UserOrderType
import com.musinsa.user.vo.UserSignUpType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceV1(
    private val repository: UserRepositoryFacade,
    private val converter: UserConverter,
    private val validator: UserBusinessValidator,
    private val updater: UserUpdater,
    private val passwordEncoder: BCryptPasswordEncoder,
) : UserService {

    @Transactional
    override fun createUser(request: CreateUserRequest): Long {
        when (request.signUpType) {
            UserSignUpType.GENERAL -> {
                validator.validateUsernameExists(username = request.username!!)
            }

            else -> {
                validator.validateSocialIdExists(socialId = request.socialId!!)
            }
        }
        validator.validateEmailExist(email = request.email)
        validator.validateNameExists(name = request.name)
        validator.validatePhoneExists(phone = request.phone)
        val user: User = converter.toEntity(request = request)
        return repository.save(user = user).id!!
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    override fun getUser(id: Long): UserResponse {
        val user: User = repository.findById(id = id)
        return converter.toResponse(user = user)
    }

    @Transactional
    override fun updateUser(userId: Long, request: UpdateUserRequest): Long {
        val user: User = repository.findById(id = userId)
        updater.markAsUpdate(
            request = request,
            user = user
        )
        return userId
    }

    @Transactional
    override fun resignUser(userId: Long): Boolean {
        val user: User = repository.findById(userId)
        user.delete()
        return user.isDeleted
    }

    @Transactional
    override fun changePassword(
        userId: Long,
        request: ChangeUserPasswordRequest
    ): Long {
        val user: User = repository.findById(userId)
        if (user.signUpType != UserSignUpType.GENERAL) {
            throw PolicyViolationException(
                errorCode = ErrorCode.SOCIAL_USER_CAN_NOT_CHANGE_PASSWORD,
                message = ErrorCode.SOCIAL_USER_CAN_NOT_CHANGE_PASSWORD.defaultMessage
            )
        }
        validator.validatePasswordMatch(
            rawPassword = request.currentPassword,
            encodedPassword = user.password!!
        )
        user.password = passwordEncoder.encode(request.newPassword)
        return userId
    }

    @Transactional
    override fun following(userId: Long, followingUserId: Long): Boolean {
        val user: User = repository.findById(id = userId)
        val followingUser: User = repository.findById(id = followingUserId)
        user.following(user = followingUser)
        return true
    }
}
