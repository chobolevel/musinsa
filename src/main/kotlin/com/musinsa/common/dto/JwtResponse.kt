package com.musinsa.common.dto

data class JwtResponse(
    val accessToken: String,
    val refreshToken: String,
)
