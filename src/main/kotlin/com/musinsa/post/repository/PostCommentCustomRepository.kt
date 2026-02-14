package com.musinsa.post.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.post.entity.PostComment
import com.musinsa.post.entity.QPostComment.postComment
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class PostCommentCustomRepository : QuerydslRepositorySupport(PostComment::class.java) {

    fun searchPostComments(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<PostComment> {
        return from(postComment)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchPostCommentsCount(
        booleanExpressions: Array<BooleanExpression>,
    ): Long {
        return from(postComment)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
