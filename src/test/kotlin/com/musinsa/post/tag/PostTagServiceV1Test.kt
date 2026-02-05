package com.musinsa.post.tag

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.post.converter.PostTagConverter
import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.dto.PostTagResponse
import com.musinsa.post.dto.UpdatePostTagRequest
import com.musinsa.post.entity.PostTag
import com.musinsa.post.reader.PostTagQueryFilter
import com.musinsa.post.reader.PostTagReader
import com.musinsa.post.service.impl.PostTagServiceV1
import com.musinsa.post.store.PostTagStore
import com.musinsa.post.updater.PostTagUpdater
import com.musinsa.post.vo.PostTagOrderType
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

    private val dummyPostTagResponse: PostTagResponse = DummyPostTag.toResponse()

    @Mock
    private lateinit var postTagConverter: PostTagConverter

    @Mock
    private lateinit var postTagStore: PostTagStore

    @Mock
    private lateinit var postTagReader: PostTagReader

    @Mock
    private lateinit var postTagUpdater: PostTagUpdater

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

    @Test
    fun getPostTagsTest() {
        // given
        val dummyPostTags: List<PostTag> = listOf(dummyPostTag)
        val dummyPostTagResponses: List<PostTagResponse> = listOf(dummyPostTagResponse)
        val queryFilter = PostTagQueryFilter(
            name = null
        )
        val pagination = Pagination(
            page = 1,
            size = 20
        )
        val orderTypes: List<PostTagOrderType> = emptyList()
        `when`(
            postTagReader.searchPostTags(
                queryFilter = queryFilter,
                pagination = pagination,
                orderTypes = orderTypes
            )
        ).thenReturn(dummyPostTags)
        `when`(
            postTagReader.searchPostTagsCount(
                queryFilter = queryFilter,
            )
        ).thenReturn(dummyPostTags.size.toLong())
        `when`(postTagConverter.toResponseInBatch(postTags = dummyPostTags)).thenReturn(dummyPostTagResponses)

        // when
        val result: PaginationResponse = postTagService.getPostTags(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )

        // then
        assertThat(result.page).isEqualTo(pagination.page)
        assertThat(result.size).isEqualTo(pagination.size)
        assertThat(result.data).isEqualTo(dummyPostTagResponses)
        assertThat(result.totalCount).isEqualTo(dummyPostTagResponses.size.toLong())
    }

    @Test
    fun getPostTagTest() {
        // given
        val dummyPostTagId: Long = dummyPostTag.id!!
        `when`(postTagReader.findById(id = dummyPostTagId)).thenReturn(dummyPostTag)
        `when`(postTagConverter.toResponse(postTag = dummyPostTag)).thenReturn(dummyPostTagResponse)

        // when
        val result: PostTagResponse = postTagService.getPostTag(postTagId = dummyPostTagId)

        // then
        assertThat(result.id).isEqualTo(dummyPostTagId)
    }

    @Test
    fun updatePostTagTest() {
        // given
        val dummyPostTagId: Long = dummyPostTag.id!!
        val request: UpdatePostTagRequest = DummyPostTag.toUpdateRequest()
        `when`(postTagReader.findById(id = dummyPostTagId)).thenReturn(dummyPostTag)
        `when`(
            postTagUpdater.markAsUpdate(
                request = request,
                postTag = dummyPostTag
            )
        ).thenReturn(dummyPostTag)

        // when
        val result: Long = postTagService.updatePostTag(
            postTagId = dummyPostTagId,
            request = request
        )

        // then
        assertThat(result).isEqualTo(dummyPostTagId)
    }

    @Test
    fun deletePostTagTest() {
        // given
        val dummyPostTagId: Long = dummyPostTag.id!!
        `when`(postTagReader.findById(id = dummyPostTagId)).thenReturn(dummyPostTag)

        // when
        val result: Boolean = postTagService.deletePostTag(postTagId = dummyPostTagId)

        // then
        assertThat(result).isTrue
    }
}
