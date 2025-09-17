package com.musinsa.application.converter

import com.musinsa.application.dto.ApplicationResponse
import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.entity.Application
import com.musinsa.application.util.ApplicationCredentialGenerator
import com.musinsa.common.extension.toMillis
import org.springframework.stereotype.Component

@Component
class ApplicationConverter {

    fun toEntity(request: CreateApplicationRequest): Application {
        return Application(
            name = request.name,
            key = ApplicationCredentialGenerator.generateApplicationKey(),
            secretKey = ApplicationCredentialGenerator.generateApplicationSecretKey(),
        )
    }

    fun toResponse(entity: Application): ApplicationResponse {
        return ApplicationResponse(
            id = entity.id!!,
            name = entity.name,
            key = entity.key,
            secretKey = entity.secretKey,
            createdAt = entity.createdAt!!.toMillis(),
            updatedAt = entity.updatedAt!!.toMillis()
        )
    }

    fun toResponseInBatch(entities: List<Application>): List<ApplicationResponse> {
        return entities.map { toResponse(it) }
    }
}
