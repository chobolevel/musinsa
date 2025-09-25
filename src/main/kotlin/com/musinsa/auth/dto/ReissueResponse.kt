package com.musinsa.auth.dto

data class ReissueResponse(
    val accessToken: String,
    val accessTokenExpiredAt: Long,
)
