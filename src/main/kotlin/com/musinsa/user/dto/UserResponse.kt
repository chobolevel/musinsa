package com.musinsa.user.dto

import com.musinsa.user.vo.UserGender
import com.musinsa.user.vo.UserGrade
import com.musinsa.user.vo.UserRole
import com.musinsa.user.vo.UserSignUpType
import com.musinsa.user.vo.UserStatus
import java.time.LocalDate

data class UserResponse(
    val id: Long,
    val signUpType: UserSignUpType,
    val signUpTypeName: String,
    val email: String,
    val name: String,
    val phone: String,
    val gender: UserGender,
    val genderName: String,
    val birthDate: LocalDate,
    val status: UserStatus,
    val grade: UserGrade,
    val gradeName: String,
    val role: UserRole,
    val pointBalance: Int,
    val isDeleted: Boolean,
    val followings: List<UserResponse>,
    val createdAt: Long,
    val updatedAt: Long,
)
