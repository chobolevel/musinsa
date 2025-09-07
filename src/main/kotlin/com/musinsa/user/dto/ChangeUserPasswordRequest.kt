package com.musinsa.user.dto

import jakarta.validation.constraints.NotEmpty

data class ChangeUserPasswordRequest(
    @field:NotEmpty
    val currentPassword: String,
    @field:NotEmpty
    val newPassword: String
)
