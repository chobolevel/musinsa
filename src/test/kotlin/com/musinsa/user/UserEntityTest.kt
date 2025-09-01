package com.musinsa.user

import com.musinsa.user.entity.User
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("user entity unit test")
class UserEntityTest {

    private val dummyUser: User = DummyUser.toEntity()

    @Test
    fun resignTest() {
        // given

        // when
        dummyUser.resign()

        // then
        Assertions.assertThat(dummyUser.resigned).isTrue()
    }
}
