package com.musinsa.application.dto

import com.musinsa.application.vo.member.ApplicationMemberType
import jakarta.validation.constraints.NotNull

data class AddApplicationMemberRequest(
    @field:NotNull
    val memberId: Long,
    @field:NotNull
    val memberType: ApplicationMemberType
)
