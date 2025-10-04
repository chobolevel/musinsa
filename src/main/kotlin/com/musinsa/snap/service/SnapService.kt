package com.musinsa.snap.service

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.dto.CreateSnapRequest
import com.musinsa.snap.dto.SnapResponse
import com.musinsa.snap.repository.SnapQueryFilter
import com.musinsa.snap.vo.SnapOrderType

interface SnapService {

    fun createSnap(userId: Long, request: CreateSnapRequest): Long

    fun getSnaps(
        queryFilter: SnapQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapOrderType>
    ): PaginationResponse

    fun getSnap(id: Long): SnapResponse
}
