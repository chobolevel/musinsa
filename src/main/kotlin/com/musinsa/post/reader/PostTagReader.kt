package com.musinsa.post.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.post.entity.PostTag
import com.musinsa.post.entity.QPostTag.postTag
import com.musinsa.post.repository.PostTagCustomRepository
import com.musinsa.post.repository.PostTagRepository
import com.musinsa.post.vo.PostTagOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class PostTagReader(
    private val postTagRepository: PostTagRepository,
    private val postTagCustomRepository: PostTagCustomRepository
) {

    fun searchPostTags(
        queryFilter: PostTagQueryFilter,
        pagination: Pagination,
        orderTypes: List<PostTagOrderType>
    ): List<PostTag> {
        return postTagCustomRepository.searchPostTags(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchPostTagsCount(queryFilter: PostTagQueryFilter): Long {
        return postTagCustomRepository.searchPostTagsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    fun findById(id: Long): PostTag {
        return postTagRepository.findByIdAndIsDeletedFalse(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.POST_TAG_NOT_FOUND,
            message = ErrorCode.POST_TAG_NOT_FOUND.defaultMessage
        )
    }

    fun findByIds(ids: List<Long>): List<PostTag> {
        return postTagRepository.findByIdInAndIsDeletedFalse(ids)
    }

    fun List<PostTagOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                PostTagOrderType.SORT_ORDER_ASC -> postTag.sortOrder.asc()
                PostTagOrderType.SORT_ORDER_DESC -> postTag.sortOrder.desc()
                PostTagOrderType.CREATED_AT_ASC -> postTag.createdAt.asc()
                PostTagOrderType.CREATED_AT_DESC -> postTag.createdAt.desc()
            }
        }.toTypedArray()
    }
}
