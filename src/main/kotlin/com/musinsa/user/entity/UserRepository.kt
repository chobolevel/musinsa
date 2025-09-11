package com.musinsa.user.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): User?

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun existsByName(name: String): Boolean

    fun existsByPhone(phone: String): Boolean
}
