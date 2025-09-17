package com.musinsa.application.service.impl

import com.musinsa.application.converter.ApplicationConverter
import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.entity.Application
import com.musinsa.application.entity.ApplicationRepositoryFacade
import com.musinsa.application.service.ApplicationService
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ApplicationServiceV1(
    private val repository: ApplicationRepositoryFacade,
    private val userRepository: UserRepositoryFacade,
    private val converter: ApplicationConverter
) : ApplicationService {

    @Transactional
    override fun createApplication(userId: Long, request: CreateApplicationRequest): Long {
        val user: User = userRepository.findById(id = userId)
        val application: Application = converter.toEntity(request = request)
        // 사용자 매핑하는 로직 추가 예정
        return repository.save(application).id!!
    }
}
