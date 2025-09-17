package com.musinsa.application.converter

import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.entity.Application
import com.musinsa.application.util.ApplicationCredentialGenerator
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
}
