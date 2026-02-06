package com.musinsa.user

import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserFollow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("user follow entity unit test")
class UserFollowEntityTest {

    val dummyUser: User = DummyUser.toEntity()

    private val dummyUserFollow: UserFollow = DummyUserFollow.toEntity()

    @Test
    fun assignFollowerTest() {
        // given

        // when
        dummyUserFollow.assignFollower(user = dummyUser)

        // then
        assertThat(dummyUserFollow.follower).isEqualTo(dummyUser)
    }

    @Test
    fun assignFollowingTest() {
        // given

        // when
        dummyUserFollow.assignFollowing(user = dummyUser)

        // then
        assertThat(dummyUserFollow.following).isEqualTo(dummyUser)
    }
}
