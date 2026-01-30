package com.musinsa.post.dto

data class PostResponse(
    val id: Long,
    val userId: Long,
    val userName: String,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long,
)
