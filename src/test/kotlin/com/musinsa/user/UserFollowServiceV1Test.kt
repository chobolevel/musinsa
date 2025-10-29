package com.musinsa.user

import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserFollowRepositoryFacade
import com.musinsa.user.entity.UserRepositoryFacade
import com.musinsa.user.serivce.impl.UserFollowServiceV1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@DisplayName("user follow service v1 unit test")
@ExtendWith(MockitoExtension::class)
class UserFollowServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()

    private val dummySocialUser: User = DummyUser.toSocialUserEntity()

    @Mock
    private lateinit var repository: UserFollowRepositoryFacade

    @Mock
    private lateinit var userRepository: UserRepositoryFacade

    @InjectMocks
    private lateinit var service: UserFollowServiceV1

    @Test
    fun followingTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummySocialUserId: Long = dummySocialUser.id!!
        `when`(userRepository.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(userRepository.findById(id = dummySocialUserId)).thenReturn(dummySocialUser)

        // when
        val result: Boolean = service.following(
            userId = dummyUserId,
            followingUserId = dummySocialUserId,
        )

        // then
        assertThat(result).isTrue()
    }

    @Test
    fun unFollowingTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummySocialUserId: Long = dummySocialUser.id!!
        doNothing().`when`(repository).deleteByFollowerIdAndFollowingId(
            followerId = dummyUserId,
            followingId = dummySocialUserId,
        )

        // when
        val result: Boolean = service.unFollowing(
            userId = dummyUserId,
            unFollowingUserId = dummySocialUserId,
        )

        // then
        assertThat(result).isTrue()
    }
}
