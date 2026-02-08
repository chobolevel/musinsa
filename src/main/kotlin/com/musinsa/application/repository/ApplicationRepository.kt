package com.musinsa.application.repository

import com.musinsa.application.entity.Application
import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationRepository : JpaRepository<Application, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): Application?

    fun findByKeyAndIsDeletedFalse(key: String): Application?
}
