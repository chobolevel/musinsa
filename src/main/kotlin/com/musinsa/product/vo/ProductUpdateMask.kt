package com.musinsa.product.vo

enum class ProductUpdateMask(val fieldName: String,) {
    PRODUCT_BRAND("product_brand_id"),
    PRODUCT_CATEGORY("product_category_id"),
    NAME("name"),
    DESCRIPTION("description"),
    STANDARD_PRICE("standard_price"),
    SALE_STATUS("sale_status"),
    SORT_ORDER("sort_order"),
    OPTION("option"),
    IMAGE("image")
}
