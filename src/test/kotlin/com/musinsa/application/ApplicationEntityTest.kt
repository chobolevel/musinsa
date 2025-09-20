package com.musinsa.application

import com.musinsa.application.entity.Application
import com.musinsa.application.vo.member.ApplicationMemberType
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("application entity unit test")
class ApplicationEntityTest {

    private val dummyUser: User = DummyUser.toEntity()
    private val dummySocialUser: User = DummyUser.toSocialUserEntity()

    private val dummyApplication: Application = DummyApplication.toEntity()

    @BeforeEach
    fun init() {
        // 테스트 엔티티 싱글턴으로 필드 공유 이슈
        dummyApplication.applicationMembers.clear()
    }

    @Test
    fun deleteTest() {
        // given

        // when
        dummyApplication.delete()

        // then
        assertThat(dummyApplication.isDeleted).isTrue()
    }

    @Test
    fun isOwnerMemberTest() {
        // given
        val dummyUser: User = DummyUser.toEntity()
        val memberType: ApplicationMemberType = ApplicationMemberType.OWNER
        dummyApplication.addMember(
            user = dummyUser,
            memberType = memberType
        )

        // when
        val isOwnerMember: Boolean = dummyApplication.isOwnerMember(memberId = dummyUser.id!!)

        // then
        assertThat(isOwnerMember).isTrue()
    }

    @Test
    fun addMemberTest() {
        // given
        val memberType: ApplicationMemberType = ApplicationMemberType.OWNER

        // when
        dummyApplication.addMember(
            user = dummyUser,
            memberType = memberType
        )

        // then
        assertThat(dummyApplication.applicationMembers.get(0).user).isEqualTo(dummyUser)
        assertThat(dummyApplication.applicationMembers.get(0).type).isEqualTo(memberType)
    }

    @Test
    fun removeMemberTest() {
        // given
        dummyApplication.addMember(
            user = dummyUser,
            memberType = ApplicationMemberType.OWNER
        )
        dummyApplication.addMember(
            user = dummySocialUser,
            memberType = ApplicationMemberType.MEMBER
        )

        // when
        dummyApplication.removeMember(
            user = dummySocialUser
        )

        // then
        assertThat(dummyApplication.applicationMembers.size).isEqualTo(1)
        assertThat(dummyApplication.applicationMembers[0].user).isEqualTo(dummyUser)
    }
}
