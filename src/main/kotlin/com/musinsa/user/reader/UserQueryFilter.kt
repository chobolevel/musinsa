package com.musinsa.user.reader

import com.musinsa.user.entity.QUser
import com.musinsa.user.vo.UserGender
import com.musinsa.user.vo.UserGrade
import com.musinsa.user.vo.UserRole
import com.musinsa.user.vo.UserSignUpType
import com.musinsa.user.vo.UserStatus
import com.querydsl.core.types.dsl.BooleanExpression

data class UserQueryFilter(
    private val signUpType: UserSignUpType?,
    private val email: String?,
    private val name: String?,
    private val phone: String?,
    private val gender: UserGender?,
    private val status: UserStatus?,
    private val grade: UserGrade?,
    private val role: UserRole?,
) {

    fun toBooleanExpressions(): Array<BooleanExpression> {
        return listOfNotNull(
            signUpType?.let { QUser.user.signUpType.eq(it) },
            email?.let { QUser.user.email.eq(it) },
            name?.let { QUser.user.name.eq(it) },
            phone?.let { QUser.user.phone.eq(it) },
            status?.let { QUser.user.status.eq(it) },
            grade?.let { QUser.user.grade.eq(it) },
            role?.let { QUser.user.role.eq(it) }
        ).toTypedArray()
    }
}
