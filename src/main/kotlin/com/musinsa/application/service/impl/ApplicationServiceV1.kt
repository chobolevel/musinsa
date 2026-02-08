package com.musinsa.application.service.impl

import com.musinsa.application.converter.ApplicationConverter
import com.musinsa.application.dto.AddApplicationMemberRequest
import com.musinsa.application.dto.ApplicationResponse
import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.dto.UpdateApplicationRequest
import com.musinsa.application.entity.Application
import com.musinsa.application.entity.ApplicationQueryFilter
import com.musinsa.application.reader.ApplicationReader
import com.musinsa.application.service.ApplicationService
import com.musinsa.application.store.ApplicationStore
import com.musinsa.application.updater.ApplicationUpdater
import com.musinsa.application.vo.ApplicationOrderType
import com.musinsa.application.vo.member.ApplicationMemberType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.PolicyViolationException
import com.musinsa.user.entity.User
import com.musinsa.user.reader.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ApplicationServiceV1(
    private val applicationStore: ApplicationStore,
    private val applicationReader: ApplicationReader,
    private val userReader: UserReader,
    private val converter: ApplicationConverter,
    private val updater: ApplicationUpdater
) : ApplicationService {

    @Transactional
    override fun createApplication(userId: Long, request: CreateApplicationRequest): Long {
        val user: User = userReader.findById(id = userId)
        val application: Application = converter.toEntity(request = request)
        application.addMember(
            user = user,
            memberType = ApplicationMemberType.OWNER
        )
        return applicationStore.save(application).id!!
    }

    @Transactional(readOnly = true)
    override fun getApplications(
        queryFilter: ApplicationQueryFilter,
        pagination: Pagination,
        orderTypes: List<ApplicationOrderType>
    ): PaginationResponse {
        val applications: List<Application> = applicationReader.searchApplications(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = applicationReader.searchApplicationsCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(entities = applications),
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    override fun getApplication(
        userId: Long,
        applicationId: Long
    ): ApplicationResponse {
        val application: Application = applicationReader.findById(id = applicationId)
        return converter.toResponse(entity = application)
    }

    @Transactional
    override fun updateApplication(
        userId: Long,
        applicationId: Long,
        request: UpdateApplicationRequest
    ): Long {
        val application: Application = applicationReader.findById(id = applicationId)
        updater.markAsUpdate(entity = application, request = request)
        return applicationId
    }

    @Transactional
    override fun deleteApplication(userId: Long, applicationId: Long): Boolean {
        val application: Application = applicationReader.findById(id = applicationId)
        application.delete()
        return application.isDeleted
    }

    @Transactional
    override fun addMember(
        userId: Long,
        applicationId: Long,
        request: AddApplicationMemberRequest
    ): Boolean {
        val application = applicationReader.findById(id = applicationId)
        if (!application.isOwnerMember(memberId = userId)) {
            throw PolicyViolationException(
                errorCode = ErrorCode.ACCESSIBLE_ONLY_OWNER_MEMBER_ON_APPLICATION,
                message = ErrorCode.ACCESSIBLE_ONLY_OWNER_MEMBER_ON_APPLICATION.defaultMessage
            )
        }
        val userToAdd: User = userReader.findById(id = request.memberId)
        application.addMember(
            user = userToAdd,
            memberType = request.memberType
        )
        return true
    }

    @Transactional
    override fun removeMember(
        userId: Long,
        applicationId: Long,
        applicationMemberId: Long
    ): Boolean {
        val application: Application = applicationReader.findById(id = applicationId)
        if (!application.isOwnerMember(memberId = userId)) {
            throw PolicyViolationException(
                errorCode = ErrorCode.ACCESSIBLE_ONLY_OWNER_MEMBER_ON_APPLICATION,
                message = ErrorCode.ACCESSIBLE_ONLY_OWNER_MEMBER_ON_APPLICATION.defaultMessage
            )
        }
        val userToRemove: User = userReader.findById(id = applicationMemberId)
        application.removeMember(
            user = userToRemove
        )
        return true
    }
}
