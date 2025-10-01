package com.musinsa.common.dto

data class UploadResponseDto(
    val presignedUrl: String,
    val url: String,
    val filenameWithExtension: String
)
