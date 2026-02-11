package com.musinsa.post.image

import com.musinsa.post.DummyPost
import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostImage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("PostImageEntity unit test")
class PostImageEntityTest {

    private val dummyPostImage: PostImage = DummyPostImage.toEntity()

    @Test
    fun assignPostTest() {
        // given
        val dummyPost: Post = DummyPost.toEntity()

        // when
        dummyPostImage.assignPost(post = dummyPost)

        // then
        assertThat(dummyPostImage.post).isEqualTo(dummyPost)
    }
}
