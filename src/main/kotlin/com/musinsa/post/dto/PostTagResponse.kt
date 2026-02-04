package com.musinsa.post.dto

data class PostTagResponse(
    val id: Long,
    val name: String,
    val sortOrder: Int,
    val createdAt: Long,
    val updatedAt: Long
)
