package com.musinsa.post.reader

import com.musinsa.common.dto.Pagination
import com.musinsa.common.exception.DataNotFoundException
import com.musinsa.common.exception.ErrorCode
import com.musinsa.post.entity.PostComment
import com.musinsa.post.entity.QPostComment.postComment
import com.musinsa.post.repository.PostCommentCustomRepository
import com.musinsa.post.repository.PostCommentRepository
import com.musinsa.post.vo.PostCommentOrderType
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class PostCommentReader(
    private val postCommentRepository: PostCommentRepository,
    private val postCommentCustomRepository: PostCommentCustomRepository
) {

    fun searchPostComments(
        queryFilter: PostCommentQueryFilter,
        pagination: Pagination,
        orderTypes: List<PostCommentOrderType>
    ): List<PostComment> {
        return postCommentCustomRepository.searchPostComments(
            booleanExpressions = queryFilter.toBooleanExpressions(),
            pagination = pagination,
            orderSpecifiers = orderTypes.toOrderSpecifiers()
        )
    }

    fun searchPostCommentsCount(queryFilter: PostCommentQueryFilter): Long {
        return postCommentCustomRepository.searchPostCommentsCount(
            booleanExpressions = queryFilter.toBooleanExpressions(),
        )
    }

    fun findById(id: Long): PostComment {
        return postCommentRepository.findByIdAndIsDeletedFalse(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.POST_COMMENT_NOT_FOUND,
            message = ErrorCode.POST_COMMENT_NOT_FOUND.defaultMessage
        )
    }

    private fun List<PostCommentOrderType>.toOrderSpecifiers(): Array<OrderSpecifier<*>> {
        return this.map {
            when (it) {
                PostCommentOrderType.CREATED_AT_ASC -> postComment.createdAt.asc()
                PostCommentOrderType.CREATED_AT_DESC -> postComment.createdAt.desc()
            }
        }.toTypedArray()
    }
}
