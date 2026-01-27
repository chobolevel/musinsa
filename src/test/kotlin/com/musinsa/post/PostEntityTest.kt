package com.musinsa.post

import com.musinsa.post.entity.Post
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("psot entity unit test")
class PostEntityTest {

    private val dummyPost: Post = DummyPost.toEntity()

    @Test
    fun assignUserTest() {
        // given
        val dummyUser: User = DummyUser.toEntity()

        // when
        dummyPost.assignUser(user = dummyUser)

        // then
        assertThat(dummyPost.user).isEqualTo(dummyUser)
    }

    @Test
    fun deleteTest() {
        // given & when
        dummyPost.delete()

        // then
        assertThat(dummyPost.isDeleted).isTrue()
    }
}
