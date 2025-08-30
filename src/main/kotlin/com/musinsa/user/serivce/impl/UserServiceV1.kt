package com.musinsa.user.serivce.impl

import com.musinsa.user.converter.UserConverter
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.entity.User
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
}
