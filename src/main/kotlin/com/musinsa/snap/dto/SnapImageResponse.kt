package com.musinsa.snap.dto

data class SnapImageResponse(
    val id: Long,
    val path: String,
    val url: String,
    val width: Int,
    val height: Int,
    val sortOrder: Int,
    val createdAt: Long,
    val updatedAt: Long,
)
