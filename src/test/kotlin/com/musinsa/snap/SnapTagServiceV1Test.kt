package com.musinsa.snap

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.converter.SnapTagConverter
import com.musinsa.snap.dto.CreateSnapTagRequest
import com.musinsa.snap.dto.SnapTagResponse
import com.musinsa.snap.dto.UpdateSnapTagRequest
import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.repository.SnapTagQueryFilter
import com.musinsa.snap.repository.SnapTagRepositoryFacade
import com.musinsa.snap.service.impl.SnapTagServiceV1
import com.musinsa.snap.updater.SnapTagUpdater
import com.musinsa.snap.vo.SnapTagOrderType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@DisplayName("snap tag service v1 unit test")
@ExtendWith(MockitoExtension::class)
class SnapTagServiceV1Test {

    private val dummySnapTag: SnapTag = DummySnapTag.toEntity()

    private val dummySnapTagResponse: SnapTagResponse = DummySnapTag.toResponse()

    @Mock
    private lateinit var repository: SnapTagRepositoryFacade

    @Mock
    private lateinit var converter: SnapTagConverter

    @Mock
    private lateinit var updater: SnapTagUpdater

    @InjectMocks
    private lateinit var service: SnapTagServiceV1

    @Test
    fun createSnapTagTest() {
        // given
        val dummyRequest: CreateSnapTagRequest = DummySnapTag.toCreateRequest()
        `when`(converter.toEntity(request = dummyRequest)).thenReturn(dummySnapTag)
        `when`(repository.save(snapTag = dummySnapTag)).thenReturn(dummySnapTag)

        // when
        val result: Long = service.createSnapTag(request = dummyRequest)

        // then
        assertThat(result).isEqualTo(dummySnapTag.id)
    }

    @Test
    fun getSnapTagsTest() {
        // given
        val queryFilter = SnapTagQueryFilter(
            type = null
        )
        val pagination = Pagination(
            page = 1,
            size = 10
        )
        val orderTypes: List<SnapTagOrderType> = emptyList()
        val dummySnapTags: List<SnapTag> = listOf(dummySnapTag)
        val dummySnapTagResponses: List<SnapTagResponse> = listOf(dummySnapTagResponse)
        `when`(
            repository.searchSnapTags(
                queryFilter = queryFilter,
                pagination = pagination,
                orderTypes = orderTypes
            )
        ).thenReturn(dummySnapTags)
        `when`(
            repository.searchSnapTagsCount(
                queryFilter = queryFilter,
            )
        ).thenReturn(dummySnapTags.size.toLong())
        `when`(converter.toResponseInBatch(entities = dummySnapTags)).thenReturn(dummySnapTagResponses)

        // when
        val result: PaginationResponse = service.getSnapTags(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )

        // then
        assertThat(result.page).isEqualTo(pagination.page)
        assertThat(result.size).isEqualTo(pagination.size)
        assertThat(result.data).isEqualTo(dummySnapTagResponses)
        assertThat(result.totalCount).isEqualTo(dummySnapTagResponses.size.toLong())
    }

    @Test
    fun getSnapTagTest() {
        // given
        val dummySnapTagId: Long = dummySnapTag.id!!
        `when`(repository.findById(id = dummySnapTagId)).thenReturn(dummySnapTag)
        `when`(converter.toResponse(entity = dummySnapTag)).thenReturn(dummySnapTagResponse)

        // when
        val result: SnapTagResponse = service.getSnapTag(id = dummySnapTagId)

        // then
        assertThat(result.id).isEqualTo(dummySnapTagId)
    }

    @Test
    fun updateSnapTagTest() {
        // given
        val dummySnapTagId: Long = dummySnapTag.id!!
        val request: UpdateSnapTagRequest = DummySnapTag.toUpdateRequest()
        `when`(repository.findById(id = dummySnapTagId)).thenReturn(dummySnapTag)
        `when`(updater.markAsUpdate(request = request, entity = dummySnapTag)).thenReturn(dummySnapTag)

        // when
        val result: Long = service.updateSnapTag(snapTagId = dummySnapTagId, request = request)

        // then
        assertThat(result).isEqualTo(dummySnapTagId)
    }

    @Test
    fun deleteSnapTagTest() {
        // given
        val dummySnapTagId: Long = dummySnapTag.id!!
        `when`(repository.findById(id = dummySnapTagId)).thenReturn(dummySnapTag)

        // when
        val result: Boolean = service.deleteSnapTag(snapTagId = dummySnapTagId)

        // then
        assertThat(result).isTrue()
    }
}
