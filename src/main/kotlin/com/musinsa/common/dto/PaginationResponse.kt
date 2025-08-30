package com.musinsa.common.dto

data class PaginationResponse(
    val page: Long,
    val size: Long,
    val data: List<Any>,
    val totalCount: Long,
)
