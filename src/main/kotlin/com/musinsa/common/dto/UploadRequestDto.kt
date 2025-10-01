package com.musinsa.common.dto

data class UploadRequestDto(
    val prefix: String,
    val filename: String,
    val extension: String
)
