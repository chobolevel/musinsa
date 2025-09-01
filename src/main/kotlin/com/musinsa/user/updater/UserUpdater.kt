package com.musinsa.user.updater

import com.musinsa.user.dto.UpdateUserRequest
import com.musinsa.user.entity.User
import com.musinsa.user.vo.UserUpdateMask
import org.springframework.stereotype.Component

@Component
class UserUpdater {

    fun markAsUpdate(request: UpdateUserRequest, user: User): User {
        request.updateMask.forEach {
            when (it) {
                UserUpdateMask.NICKNAME -> user.nickname = request.nickname!!
            }
        }
        return user
    }
}
