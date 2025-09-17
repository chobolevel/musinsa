package com.musinsa.application.entity

import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationRepository : JpaRepository<Application, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): Application?

    fun findByKeyAndIsDeletedFalse(key: String): Application?
}
