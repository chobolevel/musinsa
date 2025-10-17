package com.musinsa.snap.service

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.repository.SnapLikeQueryFilter
import com.musinsa.snap.vo.SnapLikeOrderType

interface SnapLikeService {

    fun getSnapLikes(
        queryFilter: SnapLikeQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapLikeOrderType>
    ): PaginationResponse

    fun likeSnap(userId: Long, snapId: Long): Long

    fun dislikeSnap(userId: Long, snapId: Long): Boolean
}
