package com.musinsa.user.dto

import com.musinsa.user.vo.UserGender
import com.musinsa.user.vo.UserUpdateMask
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class UpdateUserRequest(
    val email: String?,
    val name: String?,
    val phone: String?,
    val gender: UserGender?,
    val birthDate: LocalDate?,
    @field:Size(min = 1)
    val updateMask: List<UserUpdateMask>
)
