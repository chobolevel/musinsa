package com.musinsa.auth.dto

data class JwtResponse(
    val accessToken: String,
    val refreshToken: String,
)
