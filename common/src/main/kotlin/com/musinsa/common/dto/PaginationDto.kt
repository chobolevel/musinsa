package com.musinsa.common.dto

data class PaginationDto(
    val page: Long,
    val size: Long,
) {
    val offset: Long = (page - 1) * size
    val limit: Long = size
}
