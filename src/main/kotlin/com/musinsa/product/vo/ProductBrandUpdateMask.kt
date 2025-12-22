package com.musinsa.product.vo

enum class ProductBrandUpdateMask(val fieldName: String) {
    NAME("name"),
    ENGLISH_NAME("english_name"),
    YEAR_OF_LAUNCH("year_of_launch"),
    NATION("nation"),
    DESCRIPTION("description")
}
