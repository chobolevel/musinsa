package com.musinsa.snap.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.PolicyViolationException
import com.musinsa.snap.assembler.SnapLikeAssembler
import com.musinsa.snap.converter.SnapLikeConverter
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapLike
import com.musinsa.snap.reader.SnapLikeQueryFilter
import com.musinsa.snap.reader.SnapLikeReader
import com.musinsa.snap.reader.SnapReader
import com.musinsa.snap.service.SnapLikeService
import com.musinsa.snap.store.SnapLikeStore
import com.musinsa.snap.vo.SnapLikeOrderType
import com.musinsa.user.entity.User
import com.musinsa.user.reader.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SnapLikeServiceV1(
    private val snapLikeStore: SnapLikeStore,
    private val snapLikeReader: SnapLikeReader,
    private val snapReader: SnapReader,
    private val converter: SnapLikeConverter,
    private val assembler: SnapLikeAssembler,
    private val userReader: UserReader
) : SnapLikeService {

    @Transactional(readOnly = true)
    override fun getSnapLikes(
        queryFilter: SnapLikeQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapLikeOrderType>
    ): PaginationResponse {
        val snapLikes: List<SnapLike> = snapLikeReader.searchSnapLikes(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = snapLikeReader.searchSnapLikesCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(snapLikes = snapLikes),
            totalCount = totalCount,
        )
    }

    @Transactional
    override fun likeSnap(userId: Long, snapId: Long): Long {
        val user: User = userReader.findById(id = userId)
        val snap: Snap = snapReader.findById(id = snapId)
        val isExists = snapLikeReader.existsBySnapIdAndUserId(
            snapId = snapId,
            userId = userId
        )
        if (isExists) {
            throw PolicyViolationException(
                errorCode = ErrorCode.ALREADY_LIKED_SNAP,
                message = ErrorCode.ALREADY_LIKED_SNAP.defaultMessage
            )
        }
        val baseSnapLike: SnapLike = SnapLike()
        val snapLike: SnapLike = assembler.assemble(
            snapLike = baseSnapLike,
            snap = snap,
            user = user
        )
        return snapLikeStore.save(snapLike).id!!
    }

    @Transactional
    override fun dislikeSnap(userId: Long, snapId: Long): Boolean {
        val isExists = snapLikeReader.existsBySnapIdAndUserId(
            snapId = snapId,
            userId = userId
        )
        if (!isExists) {
            throw PolicyViolationException(
                errorCode = ErrorCode.NOT_LIKED_SNAP,
                message = ErrorCode.NOT_LIKED_SNAP.defaultMessage
            )
        }
        snapLikeStore.deleteBySnapIdAndUserId(
            snapId = snapId,
            userId = userId
        )
        return true
    }
}
