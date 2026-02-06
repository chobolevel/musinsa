package com.musinsa.user.repository

import com.musinsa.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): User?

    fun findByUsernameAndIsDeletedFalse(username: String): User?

    fun findBySocialIdAndIsDeletedFalse(socialId: String): User?

    fun existsByUsername(username: String): Boolean

    fun existsBySocialId(socialId: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun existsByName(name: String): Boolean

    fun existsByPhone(phone: String): Boolean
}
