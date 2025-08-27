package com.musinsa.common.dto

data class Pagination(
    private val page: Long,
    private val size: Long
) {
    val offset: Long = (page - 1) * size
    val limit: Long = size
}
