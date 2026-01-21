package com.musinsa.product.vo

enum class ProductImageUpdateMask(val fieldName: String) {
    PATH("path"),
    WIDTH("width"),
    HEIGHT("height"),
    SORT_ORDER("sort_order")
}
