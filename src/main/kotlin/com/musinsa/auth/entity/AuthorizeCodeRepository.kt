package com.musinsa.auth.entity

import org.springframework.data.jpa.repository.JpaRepository

interface AuthorizeCodeRepository : JpaRepository<AuthorizeCode, Long> {

    fun findByCode(code: String): AuthorizeCode?
}
