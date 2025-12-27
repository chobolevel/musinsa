package com.musinsa.snap.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.converter.SnapCommentConverter
import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.dto.SnapCommentResponse
import com.musinsa.snap.dto.UpdateSnapCommentRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapComment
import com.musinsa.snap.repository.SnapCommentQueryFilter
import com.musinsa.snap.repository.SnapCommentRepositoryFacade
import com.musinsa.snap.repository.SnapRepositoryFacade
import com.musinsa.snap.service.SnapCommentService
import com.musinsa.snap.updater.SnapCommentUpdater
import com.musinsa.snap.validator.SnapCommentBusinessValidator
import com.musinsa.snap.vo.SnapCommentOrderType
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SnapCommentServiceV1(
    private val repository: SnapCommentRepositoryFacade,
    private val userRepository: UserRepositoryFacade,
    private val snapRepository: SnapRepositoryFacade,
    private val converter: SnapCommentConverter,
    private val validator: SnapCommentBusinessValidator,
    private val updater: SnapCommentUpdater
) : SnapCommentService {

    @Transactional
    override fun createSnapComment(
        userId: Long,
        snapId: Long,
        request: CreateSnapCommentRequest
    ): Long {
        val base: SnapComment = converter.toEntity(request = request)
        val user: User = userRepository.findById(id = userId)
        val snap: Snap = snapRepository.findById(id = snapId)
        val parentSnapComment: SnapComment? = request.parentId?.let { parentId ->
            repository.findById(id = parentId)
        }
        val snapComment: SnapComment = SnapComment.create(
            snapComment = base,
            snap = snap,
            writer = user,
            parent = parentSnapComment,
        )
        return repository.save(snapComment = snapComment).id!!
    }

    @Transactional(readOnly = true)
    override fun getSnapComments(
        queryFilter: SnapCommentQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapCommentOrderType>
    ): PaginationResponse {
        val snapComments: List<SnapComment> = repository.searchSnapComments(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = repository.searchSnapCommentsCount(queryFilter = queryFilter)
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(snapComments = snapComments),
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    override fun getSnapComment(snapCommentId: Long): SnapCommentResponse {
        val snapComment: SnapComment = repository.findById(id = snapCommentId)
        return converter.toResponse(snapComment = snapComment)
    }

    @Transactional
    override fun updateSnapComment(
        userId: Long,
        snapCommentId: Long,
        request: UpdateSnapCommentRequest
    ): Long {
        val snapComment: SnapComment = repository.findById(id = snapCommentId)
        validator.validateWriter(
            userId = userId,
            snapComment = snapComment,
        )
        updater.markAsUpdate(
            request = request,
            snapComment = snapComment
        )
        return snapCommentId
    }

    @Transactional
    override fun deleteSnapComment(userId: Long, snapCommentId: Long): Boolean {
        val snapComment: SnapComment = repository.findById(id = snapCommentId)
        validator.validateWriter(
            userId = userId,
            snapComment = snapComment,
        )
        snapComment.delete()
        return snapComment.isDeleted
    }
}
