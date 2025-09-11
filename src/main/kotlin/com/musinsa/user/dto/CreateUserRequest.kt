package com.musinsa.user.dto

import com.musinsa.user.vo.UserGender
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class CreateUserRequest(
    @field:NotEmpty
    val username: String,
    @field:NotEmpty
    val password: String,
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
