package com.musinsa.user

import com.musinsa.user.dto.ChangeUserPasswordRequest
import com.musinsa.user.dto.CreateSocialUserRequest
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UpdateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.entity.User
import com.musinsa.user.vo.UserGender
import com.musinsa.user.vo.UserGrade
import com.musinsa.user.vo.UserRole
import com.musinsa.user.vo.UserSignUpType
import com.musinsa.user.vo.UserStatus
import com.musinsa.user.vo.UserUpdateMask
import java.time.LocalDate

object DummyUser {
    private val id: Long = 1L
    private val username: String = "rodaka"
    private val password: String = "jik584697@"
    private val socialId: String = "1234567890"
    private val signUpType: UserSignUpType = UserSignUpType.GENERAL
    private val email: String = "rodaka123@naver.com"
    private val name: String = "오늘은블루시계"
    private val phone: String = "01095657072"
    private val gender: UserGender = UserGender.MALE
    private val birthDate: LocalDate = LocalDate.of(2000, 2, 18)
    private val status: UserStatus = UserStatus.ACTIVATE
    private val grade: UserGrade = UserGrade.FAMILY
    private val role: UserRole = UserRole.ROLE_USER
    private val pointBalance: Int = 5913
    private val isDeleted: Boolean = false
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummyCreateRequest: CreateUserRequest = CreateUserRequest(
        username = username,
        password = password,
        email = email,
        name = name,
        phone = phone,
        gender = gender,
        birthDate = birthDate,
    )

    private val dummyCreateSocialUserRequest: CreateSocialUserRequest = CreateSocialUserRequest(
        signUpType = UserSignUpType.KAKAO,
        socialId = socialId,
        email = email,
        name = name,
        phone = phone,
        gender = gender,
        birthDate = birthDate,
    )

    private val dummyEntity: User = User(
        username = username,
        password = password,
        socialId = null,
        signUpType = signUpType,
        email = email,
        name = name,
        phone = phone,
        gender = gender,
        birthDate = birthDate,
        status = status,
        grade = grade,
        role = role,
        pointBalance = pointBalance,
    ).also { it.id = id }

    private val dummySocialUserEntity: User = User(
        username = null,
        password = null,
        socialId = socialId,
        signUpType = signUpType,
        email = email,
        name = name,
        phone = phone,
        gender = gender,
        birthDate = birthDate,
        status = status,
        grade = grade,
        role = role,
        pointBalance = pointBalance,
    ).also { it.id = id }

    private val dummyResponse: UserResponse = UserResponse(
        id = id,
        signUpType = signUpType,
        signUpTypeName = signUpType.desc,
        email = email,
        name = name,
        phone = phone,
        gender = gender,
        genderName = gender.desc,
        birthDate = birthDate,
        status = status,
        grade = grade,
        role = role,
        pointBalance = pointBalance,
        isDeleted = isDeleted,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

    private val dummyUpdateRequest: UpdateUserRequest = UpdateUserRequest(
        email = null,
        name = "왕감자",
        phone = null,
        gender = null,
        birthDate = null,
        updateMask = listOfNotNull(UserUpdateMask.NAME)
    )

    private val dummyChangePasswordRequest: ChangeUserPasswordRequest = ChangeUserPasswordRequest(
        currentPassword = password,
        newPassword = "newPassword"
    )

    fun toCreateRequest(): CreateUserRequest {
        return dummyCreateRequest
    }

    fun toCreateSocialUserRequest(): CreateSocialUserRequest {
        return dummyCreateSocialUserRequest
    }

    fun toEntity(): User {
        return dummyEntity
    }

    fun toSocialUserEntity(): User {
        return dummySocialUserEntity
    }

    fun toResponse(): UserResponse {
        return dummyResponse
    }

    fun toUpdateRequest(): UpdateUserRequest {
        return dummyUpdateRequest
    }

    fun toChangePasswordRequest(): ChangeUserPasswordRequest {
        return dummyChangePasswordRequest
    }
}
