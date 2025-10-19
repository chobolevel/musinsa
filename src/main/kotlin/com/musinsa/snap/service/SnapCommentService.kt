package com.musinsa.snap.service

import com.musinsa.snap.dto.CreateSnapCommentRequest

interface SnapCommentService {

    fun createSnapComment(
        userId: Long,
        snapId: Long,
        request: CreateSnapCommentRequest
    ): Long
}
