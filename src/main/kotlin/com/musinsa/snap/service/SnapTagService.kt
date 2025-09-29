package com.musinsa.snap.service

import com.musinsa.snap.dto.CreateSnapTagRequest

interface SnapTagService {

    fun createSnapTag(request: CreateSnapTagRequest): Long
}
