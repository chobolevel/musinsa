package com.musinsa.product.vo

enum class ProductCategoryUpdateMask(val fieldName: String) {
    PARENT("parent_id"),
    NAME("name"),
    ICON_IMAGE_PATH("icon_image_path")
}
