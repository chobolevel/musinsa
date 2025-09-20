package com.musinsa.application.member

import com.musinsa.application.entity.member.ApplicationMember
import com.musinsa.application.vo.member.ApplicationMemberType

object DummyApplicationMember {
    private val id: Long = 1L
    private val type: ApplicationMemberType = ApplicationMemberType.OWNER
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummyApplicationMember: ApplicationMember = ApplicationMember(
        type = type
    )

    fun toEntity(): ApplicationMember {
        return dummyApplicationMember
    }
}
