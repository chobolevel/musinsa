package com.musinsa.user.vo

enum class UserUpdateMask(val fieldName: String) {
    EMAIL("email"),
    NAME("name"),
    PHONE("phone"),
    GENDER("gender"),
    BIRTH_DATE("birth_date"),
}
