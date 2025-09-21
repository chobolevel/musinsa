package com.musinsa.user.dto

import com.musinsa.user.vo.UserGender
import com.musinsa.user.vo.UserSignUpType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class CreateUserRequest(
    val username: String?,
    val password: String?,
    val socialId: String?,
    @field:NotNull
    val signUpType: UserSignUpType,
    @field:Email
    val email: String,
    @field:NotEmpty
    val name: String,
    @field:NotEmpty
    val phone: String,
    @field:NotNull
    val gender: UserGender,
    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    val birthDate: LocalDate,
)
