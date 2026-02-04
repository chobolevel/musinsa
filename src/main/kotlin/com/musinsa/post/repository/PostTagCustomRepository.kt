package com.musinsa.post.repository

import com.musinsa.common.dto.Pagination
import com.musinsa.post.entity.PostTag
import com.musinsa.post.entity.QPostTag.postTag
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class PostTagCustomRepository : QuerydslRepositorySupport(PostTag::class.java) {

    fun searchPostTags(
        booleanExpressions: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<PostTag> {
        return from(postTag)
            .where(*booleanExpressions)
            .orderBy(*orderSpecifiers)
            .offset(pagination.offset)
            .limit(pagination.limit)
            .fetch()
    }

    fun searchPostTagsCount(booleanExpressions: Array<BooleanExpression>): Long {
        return from(postTag)
            .where(*booleanExpressions)
            .fetchCount()
    }
}
