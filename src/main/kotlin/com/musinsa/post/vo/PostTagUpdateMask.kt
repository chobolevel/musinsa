package com.musinsa.post.vo

enum class PostTagUpdateMask(
    val fieldName: String
) {
    NAME("name"),
    SORT_ORDER("sort_order")
}
