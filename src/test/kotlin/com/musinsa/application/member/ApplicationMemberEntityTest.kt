package com.musinsa.application.member

import com.musinsa.application.DummyApplication
import com.musinsa.application.entity.Application
import com.musinsa.application.entity.member.ApplicationMember
import com.musinsa.application.vo.member.ApplicationMemberType
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("application member entity unit test")
class ApplicationMemberEntityTest {

    private val dummyApplicationMember: ApplicationMember = DummyApplicationMember.toEntity()

    @Test
    fun applicationMappingFactoryMethodTest() {
        // given
        val dummyApplication: Application = DummyApplication.toEntity()
        val dummyUser: User = DummyUser.toEntity()

        // when
        val applicationMember: ApplicationMember = ApplicationMember.create(
            application = dummyApplication,
            user = dummyUser,
            memberType = ApplicationMemberType.OWNER
        )

        // then
        assertThat(applicationMember.application).isEqualTo(dummyApplication)
        assertThat(applicationMember.user).isEqualTo(dummyUser)
        assertThat(applicationMember.type).isEqualTo(ApplicationMemberType.OWNER)
    }

    @Test
    fun applicationMemberDeleteTest() {
        // given

        // when
        dummyApplicationMember.delete()

        // then
        assertThat(dummyApplicationMember.isDeleted).isTrue()
    }
}
