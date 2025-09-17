package com.musinsa.application

import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.entity.Application

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

    private val dummyCreateRequest: CreateApplicationRequest = CreateApplicationRequest(
        name = name
    )

    fun toEntity(): Application {
        return dummyApplication
    }

    fun toCreateRequest(): CreateApplicationRequest {
        return dummyCreateRequest
    }
}
