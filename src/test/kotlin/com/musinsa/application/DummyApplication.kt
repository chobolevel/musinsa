package com.musinsa.application

import com.musinsa.application.dto.ApplicationResponse
import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.dto.UpdateApplicationRequest
import com.musinsa.application.entity.Application
import com.musinsa.application.vo.ApplicationUpdateMask

object DummyApplication {
    private val id: Long = 1L
    private val name: String = "로그인앱"
    private val key: String = "application-key"
    private val secretKey: String = "application-secret-key"
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummyApplication: Application = Application(
        name = name,
        key = key,
        secretKey = secretKey
    ).also {
        it.id = id
    }

    private val dummyApplicationResponse: ApplicationResponse = ApplicationResponse(
        id = id,
        name = name,
        key = key,
        secretKey = secretKey,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

    private val dummyCreateRequest: CreateApplicationRequest = CreateApplicationRequest(
        name = name
    )

    private val dummyUpdateRequest: UpdateApplicationRequest = UpdateApplicationRequest(
        name = "새로운 이름",
        updateMask = listOf(ApplicationUpdateMask.NAME),
    )

    fun toEntity(): Application {
        return dummyApplication
    }

    fun toResponse(): ApplicationResponse {
        return dummyApplicationResponse
    }

    fun toCreateRequest(): CreateApplicationRequest {
        return dummyCreateRequest
    }

    fun toUpdateRequest(): UpdateApplicationRequest {
        return dummyUpdateRequest
    }
}
