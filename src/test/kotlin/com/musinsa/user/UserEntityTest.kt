package com.musinsa.user

import com.musinsa.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("user entity unit test")
class UserEntityTest {

    private val dummyUser: User = DummyUser.toEntity()

    private val dummySocialUser: User = DummyUser.toSocialUserEntity()

    @Test
    fun deleteTest() {
        // given

        // when
        dummyUser.delete()

        // then
        assertThat(dummyUser.isDeleted).isTrue()
    }

    @Test
    fun followingTest() {
        // given

        // when
        dummyUser.following(user = dummySocialUser)

        // then
        assertThat(dummyUser.userFollows[0].follower).isEqualTo(dummyUser)
        assertThat(dummyUser.userFollows[0].following).isEqualTo(dummySocialUser)
    }

    @Test
    fun unFollowingTest() {
        // given
        dummyUser.following(user = dummySocialUser)

        // when
        dummyUser.unFollowing(user = dummySocialUser)

        // then
        assertThat(dummyUser.userFollows.find { it.following == dummySocialUser }).isNull()
    }
}
