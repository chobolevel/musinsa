package com.musinsa.application.dto

import com.musinsa.application.vo.member.ApplicationMemberType
import org.jetbrains.annotations.NotNull

data class AddApplicationMemberRequest(
    @field:NotNull
    val memberId: Long,
    @field:NotNull
    val memberType: ApplicationMemberType
)
