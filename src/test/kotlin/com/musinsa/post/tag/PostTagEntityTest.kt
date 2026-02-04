package com.musinsa.post.tag

import com.musinsa.post.entity.PostTag
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("PostTag entity unit test")
class PostTagEntityTest {

    private val dummyPostTag: PostTag = DummyPostTag.toEntity()

    @Test
    fun deleteTest() {
        // given & when
        dummyPostTag.delete()

        // then
        assertThat(dummyPostTag.isDeleted).isTrue()
    }
}
