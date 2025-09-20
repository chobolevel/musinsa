package com.musinsa.application.service

import com.musinsa.application.dto.AddApplicationMemberRequest
import com.musinsa.application.dto.ApplicationResponse
import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.dto.UpdateApplicationRequest
import com.musinsa.application.entity.ApplicationQueryFilter
import com.musinsa.application.vo.ApplicationOrderType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse

interface ApplicationService {

    fun createApplication(userId: Long, request: CreateApplicationRequest): Long

    fun getApplications(
        queryFilter: ApplicationQueryFilter,
        pagination: Pagination,
        orderTypes: List<ApplicationOrderType>
    ): PaginationResponse

    fun getApplication(userId: Long, applicationId: Long): ApplicationResponse

    fun updateApplication(userId: Long, applicationId: Long, request: UpdateApplicationRequest): Long

    fun deleteApplication(userId: Long, applicationId: Long): Boolean

    fun addMember(userId: Long, applicationId: Long, request: AddApplicationMemberRequest): Boolean

    fun removeMember(userId: Long, applicationId: Long, applicationMemberId: Long): Boolean
}
