package com.musinsa.user

import com.musinsa.user.assembler.UserFollowAssembler
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserFollow
import com.musinsa.user.reader.UserReader
import com.musinsa.user.serivce.impl.UserFollowServiceV1
import com.musinsa.user.store.UserFollowStore
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

    private val dummyUserFollow: UserFollow = DummyUserFollow.toEntity()

    @Mock
    private lateinit var userReader: UserReader

    @Mock
    private lateinit var userFollowStore: UserFollowStore

    @Mock
    private lateinit var userFollowAssembler: UserFollowAssembler

    @InjectMocks
    private lateinit var service: UserFollowServiceV1

    @Test
    fun followingTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummySocialUserId: Long = dummySocialUser.id!!
        dummyUserFollow.assignFollower(user = dummyUser)
        dummyUserFollow.assignFollowing(user = dummySocialUser)
        `when`(userReader.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(userReader.findById(id = dummySocialUserId)).thenReturn(dummySocialUser)
        `when`(
            userFollowAssembler.assemble(
                followerUser = dummyUser,
                followingUser = dummySocialUser
            )
        ).thenReturn(dummyUserFollow)

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
        doNothing().`when`(userFollowStore).deleteByFollowerIdAndFollowingId(
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
