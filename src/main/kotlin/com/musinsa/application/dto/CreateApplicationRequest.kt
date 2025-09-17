package com.musinsa.application.dto

import jakarta.validation.constraints.NotEmpty

data class CreateApplicationRequest(
    @field:NotEmpty
    val name: String
)
