package com.musinsa.user.converter

import com.musinsa.common.extension.toMillis
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.entity.User
import com.musinsa.user.vo.UserGrade
import com.musinsa.user.vo.UserRole
import com.musinsa.user.vo.UserSignUpType
import com.musinsa.user.vo.UserStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserConverter(
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun toEntity(request: CreateUserRequest): User {
        when (request.signUpType) {
            UserSignUpType.GENERAL -> {
                return User(
                    username = request.username,
                    password = passwordEncoder.encode(request.password),
                    socialId = null,
                    signUpType = UserSignUpType.GENERAL,
                    email = request.email,
                    name = request.name,
                    phone = request.phone,
                    gender = request.gender,
                    birthDate = request.birthDate,
                    status = UserStatus.ACTIVATE,
                    grade = UserGrade.WELCOME,
                    role = UserRole.ROLE_USER,
                    pointBalance = 0
                )
            }

            else -> {
                return User(
                    username = null,
                    password = null,
                    socialId = request.socialId,
                    signUpType = request.signUpType,
                    email = request.email,
                    name = request.name,
                    phone = request.phone,
                    gender = request.gender,
                    birthDate = request.birthDate,
                    status = UserStatus.ACTIVATE,
                    grade = UserGrade.WELCOME,
                    role = UserRole.ROLE_USER,
                    pointBalance = 0
                )
            }
        }
    }

    fun toResponse(user: User): UserResponse {
        return UserResponse(
            id = user.id!!,
            signUpType = user.signUpType,
            signUpTypeName = user.signUpType.desc,
            email = user.email,
            name = user.name,
            phone = user.phone,
            gender = user.gender,
            genderName = user.gender.desc,
            birthDate = user.birthDate,
            status = user.status,
            grade = user.grade,
            gradeName = user.grade.desc,
            role = user.role,
            pointBalance = user.pointBalance,
            isDeleted = user.isDeleted,
            followings = user.userFollows.map { toResponse(user = it.following!!) },
            createdAt = user.createdAt!!.toMillis(),
            updatedAt = user.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(users: List<User>): List<UserResponse> {
        return users.map { toResponse(it) }
    }
}
