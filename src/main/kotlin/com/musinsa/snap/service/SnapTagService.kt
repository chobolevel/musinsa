package com.musinsa.snap.service

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.dto.CreateSnapTagRequest
import com.musinsa.snap.dto.SnapTagResponse
import com.musinsa.snap.dto.UpdateSnapTagRequest
import com.musinsa.snap.repository.SnapTagQueryFilter
import com.musinsa.snap.vo.SnapTagOrderType

interface SnapTagService {

    fun createSnapTag(request: CreateSnapTagRequest): Long

    fun getSnapTags(
        queryFilter: SnapTagQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapTagOrderType>
    ): PaginationResponse

    fun getSnapTag(id: Long): SnapTagResponse

    fun updateSnapTag(snapTagId: Long, request: UpdateSnapTagRequest): Long
}
