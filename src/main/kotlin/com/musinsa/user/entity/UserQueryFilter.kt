package com.musinsa.user.entity

import com.musinsa.user.entity.QUser.user
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
            signUpType?.let { user.signUpType.eq(it) },
            email?.let { user.email.eq(it) },
            name?.let { user.name.eq(it) },
            phone?.let { user.phone.eq(it) },
            status?.let { user.status.eq(it) },
            grade?.let { user.grade.eq(it) },
            role?.let { user.role.eq(it) }
        ).toTypedArray()
    }
}
