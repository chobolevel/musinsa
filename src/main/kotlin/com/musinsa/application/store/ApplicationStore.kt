package com.musinsa.application.store

import com.musinsa.application.entity.Application
import com.musinsa.application.repository.ApplicationRepository
import org.springframework.stereotype.Component

@Component
class ApplicationStore(
    private val applicationRepository: ApplicationRepository
) {

    fun save(application: Application): Application {
        return applicationRepository.save(application)
    }
}
