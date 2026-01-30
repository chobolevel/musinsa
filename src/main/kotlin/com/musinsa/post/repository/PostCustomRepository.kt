package com.musinsa.post.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.post.entity.Post
import com.musinsa.post.entity.QPost.post
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class PostCustomRepository : QuerydslRepositorySupport(Post::class.java) {

    fun searchPosts(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<Post> {
        return from(post)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchPostsCount(booleanExpressions: Array<BooleanExpression>): Long {
        return from(post)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
