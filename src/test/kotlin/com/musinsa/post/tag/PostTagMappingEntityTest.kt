package com.musinsa.post.tag

import com.musinsa.post.DummyPost
import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostTag
import com.musinsa.post.entity.PostTagMapping
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("PostTagMappingEntity unit test")
class PostTagMappingEntityTest {

    private val dummyPostTagMapping: PostTagMapping = DummyPostTagMapping.toEntity()

    @Test
    fun assignPostTest() {
        // given
        val dummyPost: Post = DummyPost.toEntity()

        // when
        dummyPostTagMapping.assignPost(post = dummyPost)

        // then
        assertThat(dummyPostTagMapping.post).isEqualTo(dummyPost)
    }

    @Test
    fun assignPostTagTest() {
        // given
        val dummyPostTag: PostTag = DummyPostTag.toEntity()

        // when
        dummyPostTagMapping.assignPostTag(postTag = dummyPostTag)

        // then
        assertThat(dummyPostTagMapping.postTag).isEqualTo(dummyPostTag)
    }
}
