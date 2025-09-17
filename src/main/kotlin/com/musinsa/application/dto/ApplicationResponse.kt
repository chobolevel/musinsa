package com.musinsa.application.dto

data class ApplicationResponse(
    val id: Long,
    val name: String,
    val key: String,
    val secretKey: String,
    val createdAt: Long,
    val updatedAt: Long
)
