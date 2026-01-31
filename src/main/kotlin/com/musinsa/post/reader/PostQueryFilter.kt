package com.musinsa.post.reader

import com.musinsa.post.entity.QPost.post
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions

data class PostQueryFilter(
    private val userId: Long?,
    private val keyword: String?,
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            userId?.let { post.user.id.eq(it) },
            keyword?.let {
                Expressions.booleanTemplate(
                    "MATCH({0}) AGAINST({1} IN BOOLEAN MODE)",
                    post.title,
                    it
                )
            },
            keyword?.let {
                Expressions.booleanTemplate(
                    "MATCH({0}) AGAINST({1} IN BOOLEAN MODE)",
                    post.content,
                    it
                )
            }
        ).toTypedArray()
    }
}
