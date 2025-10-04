package com.musinsa.snap

import com.musinsa.snap.converter.SnapConverter
import com.musinsa.snap.dto.CreateSnapRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.repository.SnapRepositoryFacade
import com.musinsa.snap.service.impl.SnapServiceV1
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

@DisplayName("snap service v1 unit test")
@ExtendWith(MockitoExtension::class)
class SnapServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()

    private val dummySnap: Snap = DummySnap.toEntity()

    @Mock
    private lateinit var converter: SnapConverter

    @Mock
    private lateinit var userRepository: UserRepositoryFacade

    @Mock
    private lateinit var snapRepository: SnapRepositoryFacade

    @InjectMocks
    private lateinit var service: SnapServiceV1

    @Test
    fun createSnapTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyRequest: CreateSnapRequest = DummySnap.toCreateRequest()
        `when`(converter.toEntity(request = dummyRequest)).thenReturn(dummySnap)
        `when`(userRepository.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(snapRepository.save(snap = dummySnap)).thenReturn(dummySnap)

        // when
        val result: Long = service.createSnap(userId = dummyUserId, request = dummyRequest)

        // then
        assertThat(result).isEqualTo(dummySnap.id)
    }
}
