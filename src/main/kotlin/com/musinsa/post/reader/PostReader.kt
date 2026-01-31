package com.musinsa.post.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.post.entity.Post
import com.musinsa.post.entity.QPost.post
import com.musinsa.post.repository.PostCustomRepository
import com.musinsa.post.repository.PostRepository
import com.musinsa.post.vo.PostOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class PostReader(
    private val repository: PostRepository,
    private val customRepository: PostCustomRepository,
) {

    fun searchPosts(
        queryFilter: PostQueryFilter,
        pagination: Pagination,
        orderTypes: List<PostOrderType>
    ): List<Post> {
        return customRepository.searchPosts(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchPostsCount(queryFilter: PostQueryFilter): Long {
        return customRepository.searchPostsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    fun findById(id: Long): Post {
        return repository.findByIdAndIsDeletedFalse(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.POST_NOT_FOUND,
            message = ErrorCode.POST_NOT_FOUND.defaultMessage
        )
    }

    private fun List<PostOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                PostOrderType.CREATED_AT_ASC -> post.createdAt.asc()
                PostOrderType.CREATED_AT_DESC -> post.createdAt.desc()
                PostOrderType.UPDATED_AT_ASC -> post.updatedAt.asc()
                PostOrderType.UPDATED_AT_DESC -> post.updatedAt.desc()
            }
        }.toTypedArray()
    }
}
