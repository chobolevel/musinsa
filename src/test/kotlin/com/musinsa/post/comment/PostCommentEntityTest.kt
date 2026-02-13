package com.musinsa.post.comment

import com.musinsa.post.DummyPost
import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostComment
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("PostComment entity unit test")
class PostCommentEntityTest {

    private val dummyPostComment: PostComment = DummyPostComment.toEntity()

    @Test
    fun assignUserTest() {
        // given
        val dummyUser: User = DummyUser.toEntity()

        // when
        dummyPostComment.assignUser(user = dummyUser)

        // then
        assertThat(dummyPostComment.user).isEqualTo(dummyUser)
    }

    @Test
    fun assignPostTest() {
        // given
        val dummyPost: Post = DummyPost.toEntity()

        // when
        dummyPostComment.assignPost(post = dummyPost)

        // then
        assertThat(dummyPostComment.post).isEqualTo(dummyPost)
    }

    @Test
    fun assignParentTest() {
        // given
        val dummyParentPostComment: PostComment = DummyPostComment.toParentEntity()

        // when
        dummyPostComment.assignParent(parent = dummyParentPostComment)

        // then
        assertThat(dummyPostComment.parent).isEqualTo(dummyParentPostComment)
    }

    @Test
    fun deleteTest() {
        // given & when
        dummyPostComment.delete()

        // then
        assertThat(dummyPostComment.isDeleted).isTrue
    }
}
