package com.musinsa.snap.dto

data class SnapImageResponse(
    val id: Long,
    val url: String,
    val width: Int,
    val height: Int,
    val order: Int,
    val createdAt: Long,
    val updatedAt: Long,
)
