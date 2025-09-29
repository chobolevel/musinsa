package com.musinsa.snap

import com.musinsa.snap.converter.SnapTagConverter
import com.musinsa.snap.dto.CreateSnapTagRequest
import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.entity.SnapTagRepositoryFacade
import com.musinsa.snap.service.impl.SnapTagServiceV1
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

    @Mock
    private lateinit var repository: SnapTagRepositoryFacade

    @Mock
    private lateinit var converter: SnapTagConverter

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
}
