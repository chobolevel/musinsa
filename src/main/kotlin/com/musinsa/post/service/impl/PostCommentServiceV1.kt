package com.musinsa.post.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.PolicyViolationException
import com.musinsa.post.assembler.PostCommentAssembler
import com.musinsa.post.converter.PostCommentConverter
import com.musinsa.post.dto.CreatePostCommentRequest
import com.musinsa.post.dto.PostCommentResponse
import com.musinsa.post.dto.UpdatePostCommentRequest
import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostComment
import com.musinsa.post.reader.PostCommentQueryFilter
import com.musinsa.post.reader.PostCommentReader
import com.musinsa.post.reader.PostReader
import com.musinsa.post.service.PostCommentService
import com.musinsa.post.store.PostCommentStore
import com.musinsa.post.updater.PostCommentUpdater
import com.musinsa.post.vo.PostCommentOrderType
import com.musinsa.user.entity.User
import com.musinsa.user.reader.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostCommentServiceV1(
    private val postCommentConverter: PostCommentConverter,
    private val postCommentStore: PostCommentStore,
    private val userReader: UserReader,
    private val postReader: PostReader,
    private val postCommentReader: PostCommentReader,
    private val postCommentAssembler: PostCommentAssembler,
    private val postCommentUpdater: PostCommentUpdater,
) : PostCommentService {

    @Transactional
    override fun createPostComment(
        userId: Long,
        postId: Long,
        request: CreatePostCommentRequest
    ): Long {
        val basePostComment: PostComment = postCommentConverter.toEntity(request = request)
        val user: User = userReader.findById(id = userId)
        val post: Post = postReader.findById(id = postId)
        val parentPostComment: PostComment? = request.parentId?.let { postCommentReader.findById(id = it) }

        val postComment: PostComment = postCommentAssembler.assemble(
            postComment = basePostComment,
            user = user,
            post = post,
            parentPostComment = parentPostComment
        )

        return postCommentStore.save(postComment = postComment).id!!
    }

    @Transactional(readOnly = true)
    override fun getPostComments(
        queryFilter: PostCommentQueryFilter,
        pagination: Pagination,
        orderTypes: List<PostCommentOrderType>
    ): PaginationResponse {
        val postComments: List<PostComment> = postCommentReader.searchPostComments(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = postCommentReader.searchPostCommentsCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = postCommentConverter.toResponseInBatch(postComments = postComments),
            totalCount = totalCount,
        )
    }

    @Transactional(readOnly = true)
    override fun getPostComment(postCommentId: Long): PostCommentResponse {
        val postComment: PostComment = postCommentReader.findById(id = postCommentId)
        return postCommentConverter.toResponse(postComment = postComment)
    }

    @Transactional
    override fun updatePostComment(
        userId: Long,
        postCommentId: Long,
        request: UpdatePostCommentRequest
    ): Long {
        val postComment: PostComment = postCommentReader.findById(id = postCommentId)
        validateUser(
            userId = userId,
            postComment = postComment,
        )
        postCommentUpdater.markAsUpdate(
            request = request,
            postComment = postComment,
        )
        return postCommentId
    }

    private fun validateUser(
        userId: Long,
        postComment: PostComment
    ) {
        if (userId != postComment.user?.id) {
            throw PolicyViolationException(
                errorCode = ErrorCode.ACCESSIBLE_ONLY_WRITER_ON_POST_COMMENT,
                message = ErrorCode.ACCESSIBLE_ONLY_WRITER_ON_POST_COMMENT.defaultMessage
            )
        }
    }
}
