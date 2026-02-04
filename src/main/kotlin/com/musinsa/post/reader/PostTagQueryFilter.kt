package com.musinsa.post.reader

import com.musinsa.post.entity.QPostTag.postTag
import com.querydsl.core.types.dsl.BooleanExpression

data class PostTagQueryFilter(
    private val name: String?
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            name?.let { postTag.name.startsWith(it) },
            postTag.isDeleted.isFalse
        ).toTypedArray()
    }
}
