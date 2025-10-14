package com.musinsa.snap.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.converter.SnapLikeConverter
import com.musinsa.snap.entity.SnapLike
import com.musinsa.snap.repository.SnapLikeQueryFilter
import com.musinsa.snap.repository.SnapLikeRepositoryFacade
import com.musinsa.snap.service.SnapLikeService
import com.musinsa.snap.vo.SnapLikeOrderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SnapLikeServiceV1(
    private val repository: SnapLikeRepositoryFacade,
    private val converter: SnapLikeConverter,
) : SnapLikeService {

    @Transactional(readOnly = true)
    override fun getSnapLikes(
        queryFilter: SnapLikeQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapLikeOrderType>
    ): PaginationResponse {
        val snapLikes: List<SnapLike> = repository.searchSnapLikes(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = repository.searchSnapLikesCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(snapLikes = snapLikes),
            totalCount = totalCount,
        )
    }
}
