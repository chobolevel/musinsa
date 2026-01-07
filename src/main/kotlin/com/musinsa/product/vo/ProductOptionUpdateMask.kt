package com.musinsa.product.vo

enum class ProductOptionUpdateMask(val fieldName: String) {
    NAME("name"),
    SORT_ORDER("sort_order"),
    IS_REQUIRED("is_required"),
    VALUE("value")
}
