package com.musinsa.application.service

import com.musinsa.application.dto.CreateApplicationRequest

interface ApplicationService {

    fun createApplication(userId: Long, request: CreateApplicationRequest): Long
}
