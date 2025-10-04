package com.musinsa.snap.service

import com.musinsa.snap.dto.CreateSnapRequest

interface SnapService {

    fun createSnap(userId: Long, request: CreateSnapRequest): Long
}
