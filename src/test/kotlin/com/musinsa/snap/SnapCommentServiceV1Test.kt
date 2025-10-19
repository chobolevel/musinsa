package com.musinsa.snap

import com.musinsa.snap.converter.SnapCommentConverter
import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapComment
import com.musinsa.snap.repository.SnapCommentRepositoryFacade
import com.musinsa.snap.repository.SnapRepositoryFacade
import com.musinsa.snap.service.impl.SnapCommentServiceV1
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@DisplayName("snap comment service v1 unit test")
@ExtendWith(MockitoExtension::class)
class SnapCommentServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()

    private val dummySnap: Snap = DummySnap.toEntity()

    private val dummySnapComment: SnapComment = DummySnapComment.toEntity()

    @Mock
    private lateinit var repository: SnapCommentRepositoryFacade

    @Mock
    private lateinit var userRepository: UserRepositoryFacade

    @Mock
    private lateinit var snapRepository: SnapRepositoryFacade

    @Mock
    private lateinit var converter: SnapCommentConverter

    @InjectMocks
    private lateinit var service: SnapCommentServiceV1

    @Test
    fun createSnapCommentTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummySnapId: Long = dummySnap.id!!
        val dummyRequest: CreateSnapCommentRequest = DummySnapComment.toCreateRequest()
        `when`(converter.toEntity(request = dummyRequest)).thenReturn(dummySnapComment)
        `when`(userRepository.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(snapRepository.findById(id = dummySnapId)).thenReturn(dummySnap)
        `when`(repository.save(snapComment = dummySnapComment)).thenReturn(dummySnapComment)

        // when
        val result: Long = service.createSnapComment(
            userId = dummyUserId,
            snapId = dummySnapId,
            request = dummyRequest
        )

        // then
        assertThat(result).isEqualTo(dummySnapComment.id)
    }
}
