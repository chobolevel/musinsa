package com.musinsa.snap.service

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.dto.SnapCommentResponse
import com.musinsa.snap.dto.UpdateSnapCommentRequest
import com.musinsa.snap.repository.SnapCommentQueryFilter
import com.musinsa.snap.vo.SnapCommentOrderType

interface SnapCommentService {

    fun createSnapComment(
        userId: Long,
        snapId: Long,
        request: CreateSnapCommentRequest
    ): Long

    fun getSnapComments(
        queryFilter: SnapCommentQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapCommentOrderType>
    ): PaginationResponse

    fun getSnapComment(snapCommentId: Long): SnapCommentResponse

    fun updateSnapComment(
        userId: Long,
        snapCommentId: Long,
        request: UpdateSnapCommentRequest
    ): Long

    fun deleteSnapComment(
        userId: Long,
        snapCommentId: Long
    ): Boolean
}
