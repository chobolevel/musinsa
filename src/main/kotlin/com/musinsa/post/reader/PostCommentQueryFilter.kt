package com.musinsa.post.reader

import com.musinsa.post.entity.QPostComment.postComment
import com.querydsl.core.types.dsl.BooleanExpression

data class PostCommentQueryFilter(
    private val userId: Long?,
    private val postId: Long?,
    private val parentId: Long?,
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            userId?.let { postComment._user.id.eq(it) },
            postId?.let { postComment._post.id.eq(it) },
            parentId?.let { postComment._parent.id.eq(it) },
            postComment.isDeleted.isFalse
        ).toTypedArray()
    }
}
