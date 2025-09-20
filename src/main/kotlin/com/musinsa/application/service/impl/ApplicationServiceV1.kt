package com.musinsa.application.service.impl

import com.musinsa.application.converter.ApplicationConverter
import com.musinsa.application.dto.ApplicationResponse
import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.dto.UpdateApplicationRequest
import com.musinsa.application.entity.Application
import com.musinsa.application.entity.ApplicationQueryFilter
import com.musinsa.application.entity.ApplicationRepositoryFacade
import com.musinsa.application.service.ApplicationService
import com.musinsa.application.updater.ApplicationUpdater
import com.musinsa.application.vo.ApplicationOrderType
import com.musinsa.application.vo.member.ApplicationMemberType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ApplicationServiceV1(
    private val repository: ApplicationRepositoryFacade,
    private val userRepository: UserRepositoryFacade,
    private val converter: ApplicationConverter,
    private val updater: ApplicationUpdater
) : ApplicationService {

    @Transactional
    override fun createApplication(userId: Long, request: CreateApplicationRequest): Long {
        val user: User = userRepository.findById(id = userId)
        val application: Application = converter.toEntity(request = request)
        application.addMember(
            user = user,
            memberType = ApplicationMemberType.OWNER
        )
        return repository.save(application).id!!
    }

    @Transactional(readOnly = true)
    override fun getApplications(
        queryFilter: ApplicationQueryFilter,
        pagination: Pagination,
        orderTypes: List<ApplicationOrderType>
    ): PaginationResponse {
        val applications: List<Application> = repository.searchApplications(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = repository.searchApplicationsCount(
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
        val application: Application = repository.findById(id = applicationId)
        return converter.toResponse(entity = application)
    }

    @Transactional
    override fun updateApplication(
        userId: Long,
        applicationId: Long,
        request: UpdateApplicationRequest
    ): Long {
        val application: Application = repository.findById(id = applicationId)
        updater.markAsUpdate(entity = application, request = request)
        return applicationId
    }

    @Transactional
    override fun deleteApplication(userId: Long, applicationId: Long): Boolean {
        val application: Application = repository.findById(id = applicationId)
        application.delete()
        return application.isDeleted
    }
}
