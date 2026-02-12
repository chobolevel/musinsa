package com.musinsa.post.vo

enum class PostImageUpdateMask(
    val fieldName: String,
) {
    TYPE("type"),
    PATH("path"),
    WIDTH("width"),
    HEIGHT("height"),
    SORT_ORDER("sort_order"),
}
