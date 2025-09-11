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
                UserUpdateMask.EMAIL -> user.email = request.email!!
                UserUpdateMask.NAME -> user.name = request.name!!
                UserUpdateMask.PHONE -> user.phone = request.phone!!
                UserUpdateMask.GENDER -> user.gender = request.gender!!
                UserUpdateMask.BIRTH_DATE -> user.birthDate = request.birthDate!!
            }
        }
        return user
    }
}
