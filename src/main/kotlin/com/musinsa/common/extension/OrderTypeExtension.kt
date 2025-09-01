package com.musinsa.common.extension

import com.musinsa.user.entity.QUser.user
import com.musinsa.user.vo.UserOrderType
import com.querydsl.core.types.OrderSpecifier

fun List<UserOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
    return this.map { userOrderType ->
        when (userOrderType) {
            UserOrderType.CREATED_AT_ASC -> user.createdAt.asc()
            UserOrderType.CREATED_AT_DESC -> user.createdAt.desc()
            UserOrderType.UPDATED_AT_ASC -> user.updatedAt.asc()
            UserOrderType.UPDATED_AT_DESC -> user.updatedAt.desc()
        }
    }.toTypedArray()
}
