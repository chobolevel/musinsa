package com.musinsa.snap.dto

import com.musinsa.snap.vo.SnapTagType

data class SnapTagResponse(
    val id: Long,
    val type: SnapTagType,
    val name: String,
    val createdAt: Long,
    val updatedAt: Long
)
