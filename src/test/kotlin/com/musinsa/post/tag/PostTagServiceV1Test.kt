package com.musinsa.post.tag

import com.musinsa.post.converter.PostTagConverter
import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.entity.PostTag
import com.musinsa.post.service.impl.PostTagServiceV1
import com.musinsa.post.store.PostTagStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@DisplayName("PostTagServiceV1 unit test")
@ExtendWith(MockitoExtension::class)
class PostTagServiceV1Test {

    private val dummyPostTag: PostTag = DummyPostTag.toEntity()

    @Mock
    private lateinit var postTagStore: PostTagStore

    @Mock
    private lateinit var postTagConverter: PostTagConverter

    @InjectMocks
    private lateinit var postTagService: PostTagServiceV1

    @Test
    fun createPostTagTest() {
        // given
        val request: CreatePostTagRequest = DummyPostTag.toCreateRequest()
        `when`(postTagConverter.toEntity(request = request)).thenReturn(dummyPostTag)
        `when`(postTagStore.save(postTag = dummyPostTag)).thenReturn(dummyPostTag)

        // when
        val result: Long = postTagService.createPostTag(request = request)

        // then
        assertThat(result).isEqualTo(dummyPostTag.id)
    }
}
