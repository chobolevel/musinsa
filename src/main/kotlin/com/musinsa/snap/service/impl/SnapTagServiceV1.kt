package com.musinsa.snap.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.converter.SnapTagConverter
import com.musinsa.snap.dto.CreateSnapTagRequest
import com.musinsa.snap.dto.SnapTagResponse
import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.repository.SnapTagQueryFilter
import com.musinsa.snap.repository.SnapTagRepositoryFacade
import com.musinsa.snap.service.SnapTagService
import com.musinsa.snap.vo.SnapTagOrderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SnapTagServiceV1(
    private val repository: SnapTagRepositoryFacade,
    private val converter: SnapTagConverter
) : SnapTagService {

    @Transactional
    override fun createSnapTag(request: CreateSnapTagRequest): Long {
        val snapTag: SnapTag = converter.toEntity(request = request)
        return repository.save(snapTag = snapTag).id!!
    }

    override fun getSnapTags(
        queryFilter: SnapTagQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapTagOrderType>
    ): PaginationResponse {
        val snapTags: List<SnapTag> = repository.searchSnapTags(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = repository.searchSnapTagsCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(entities = snapTags),
            totalCount = totalCount
        )
    }

    override fun getSnapTag(id: Long): SnapTagResponse {
        val snapTag: SnapTag = repository.findById(id = id)
        return converter.toResponse(snapTag)
    }
}
