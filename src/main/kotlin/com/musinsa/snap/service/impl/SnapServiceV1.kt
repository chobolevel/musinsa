package com.musinsa.snap.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.PolicyViolationException
import com.musinsa.snap.converter.SnapConverter
import com.musinsa.snap.dto.CreateSnapRequest
import com.musinsa.snap.dto.SnapResponse
import com.musinsa.snap.dto.UpdateSnapRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.repository.SnapQueryFilter
import com.musinsa.snap.repository.SnapRepositoryFacade
import com.musinsa.snap.service.SnapService
import com.musinsa.snap.updater.SnapUpdater
import com.musinsa.snap.vo.SnapOrderType
import com.musinsa.user.entity.UserRepositoryFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.Throws

@Service
class SnapServiceV1(
    private val converter: SnapConverter,
    private val userRepository: UserRepositoryFacade,
    private val snapRepository: SnapRepositoryFacade,
    private val updater: SnapUpdater
) : SnapService {

    @Transactional
    override fun createSnap(userId: Long, request: CreateSnapRequest): Long {
        val snap: Snap = converter.toEntity(request = request).also {
            it.mapWriter(user = userRepository.findById(id = userId))
        }
        return snapRepository.save(snap = snap).id!!
    }

    @Transactional(readOnly = true)
    override fun getSnaps(
        queryFilter: SnapQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapOrderType>
    ): PaginationResponse {
        val snaps: List<Snap> = snapRepository.searchSnaps(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = snapRepository.searchSnapsCount(queryFilter = queryFilter)
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(snaps = snaps),
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    override fun getSnap(id: Long): SnapResponse {
        val snap: Snap = snapRepository.findById(id = id)
        return converter.toResponse(snap = snap)
    }

    @Transactional
    override fun updateSnap(userId: Long, snapId: Long, request: UpdateSnapRequest): Long {
        val snap: Snap = snapRepository.findById(id = snapId)
        validateWriterMatching(
            userId = userId,
            snapWriterId = snap.writer!!.id!!
        )
        updater.markAsUpdate(
            request = request,
            snap = snap
        )
        return snapId
    }

    @Transactional
    override fun deleteSnap(userId: Long, snapId: Long): Boolean {
        val snap: Snap = snapRepository.findById(id = snapId)
        validateWriterMatching(userId = userId, snapWriterId = snap.writer!!.id!!)
        snap.delete()
        return snap.isDeleted
    }

    @Throws(PolicyViolationException::class)
    private fun validateWriterMatching(userId: Long, snapWriterId: Long) {
        if (userId != snapWriterId) {
            throw PolicyViolationException(
                errorCode = ErrorCode.ACCESSIBLE_ONLY_WRITER_ON_SNAP,
                message = ErrorCode.ACCESSIBLE_ONLY_WRITER_ON_SNAP.defaultMessage
            )
        }
    }
}
