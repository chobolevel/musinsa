package com.musinsa.user

import com.musinsa.user.entity.UserFollow

object DummyUserFollow {
    private val id: Long = 1L

    private val dummyUserFollow: UserFollow by lazy {
        UserFollow().also {
            it.id = id
        }
    }

    fun toEntity(): UserFollow = dummyUserFollow
}
