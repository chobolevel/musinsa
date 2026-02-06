package com.musinsa.snap.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.assembler.SnapCommentAssembler
import com.musinsa.snap.converter.SnapCommentConverter
import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.dto.SnapCommentResponse
import com.musinsa.snap.dto.UpdateSnapCommentRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapComment
import com.musinsa.snap.reader.SnapCommentQueryFilter
import com.musinsa.snap.reader.SnapCommentReader
import com.musinsa.snap.reader.SnapReader
import com.musinsa.snap.service.SnapCommentService
import com.musinsa.snap.store.SnapCommentStore
import com.musinsa.snap.updater.SnapCommentUpdater
import com.musinsa.snap.validator.SnapCommentBusinessValidator
import com.musinsa.snap.vo.SnapCommentOrderType
import com.musinsa.user.entity.User
import com.musinsa.user.reader.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SnapCommentServiceV1(
    private val snapCommentStore: SnapCommentStore,
    private val snapCommentReader: SnapCommentReader,
    private val userReader: UserReader,
    private val snapReader: SnapReader,
    private val converter: SnapCommentConverter,
    private val assembler: SnapCommentAssembler,
    private val validator: SnapCommentBusinessValidator,
    private val updater: SnapCommentUpdater
) : SnapCommentService {

    @Transactional
    override fun createSnapComment(
        userId: Long,
        snapId: Long,
        request: CreateSnapCommentRequest
    ): Long {
        val baseSnapComment: SnapComment = converter.toEntity(request = request)
        val parentSnapComment: SnapComment? = request.parentId?.let { parentId ->
            snapCommentReader.findById(id = parentId)
        }
        val user: User = userReader.findById(id = userId)
        val snap: Snap = snapReader.findById(id = snapId)

        val snapComment: SnapComment = assembler.assemble(
            snapComment = baseSnapComment,
            parentSnapComment = parentSnapComment,
            snap = snap,
            writer = user,
        )

        return snapCommentStore.save(snapComment = snapComment).id!!
    }

    @Transactional(readOnly = true)
    override fun getSnapComments(
        queryFilter: SnapCommentQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapCommentOrderType>
    ): PaginationResponse {
        val snapComments: List<SnapComment> = snapCommentReader.searchSnapComments(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = snapCommentReader.searchSnapCommentsCount(queryFilter = queryFilter)
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(snapComments = snapComments),
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    override fun getSnapComment(snapCommentId: Long): SnapCommentResponse {
        val snapComment: SnapComment = snapCommentReader.findById(id = snapCommentId)
        return converter.toResponse(snapComment = snapComment)
    }

    @Transactional
    override fun updateSnapComment(
        userId: Long,
        snapCommentId: Long,
        request: UpdateSnapCommentRequest
    ): Long {
        val snapComment: SnapComment = snapCommentReader.findById(id = snapCommentId)
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
        val snapComment: SnapComment = snapCommentReader.findById(id = snapCommentId)
        validator.validateWriter(
            userId = userId,
            snapComment = snapComment,
        )
        snapComment.delete()
        return snapComment.isDeleted
    }
}
