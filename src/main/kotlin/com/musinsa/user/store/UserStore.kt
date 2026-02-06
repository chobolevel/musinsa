package com.musinsa.user.store

import com.musinsa.user.entity.User
import com.musinsa.user.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserStore(
    private val userRepository: UserRepository
) {

    fun save(user: User): User {
        return userRepository.save(user)
    }
}
