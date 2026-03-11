package com.musinsa.user

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.user.assembler.UserFollowAssembler
import com.musinsa.user.converter.UserFollowConverter
import com.musinsa.user.dto.UserFollowResponse
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserFollow
import com.musinsa.user.reader.UserFollowQueryFilter
import com.musinsa.user.reader.UserFollowReader
import com.musinsa.user.reader.UserReader
import com.musinsa.user.service.impl.UserFollowServiceV1
import com.musinsa.user.store.UserFollowStore
import com.musinsa.user.vo.UserFollowOrderType
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

    private val dummyUserFollowResponse: UserFollowResponse = DummyUserFollow.toResponse()

    @Mock
    private lateinit var userReader: UserReader

    @Mock
    private lateinit var userFollowReader: UserFollowReader

    @Mock
    private lateinit var userFollowStore: UserFollowStore

    @Mock
    private lateinit var userFollowAssembler: UserFollowAssembler

    @Mock
    private lateinit var userFollowConverter: UserFollowConverter

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

    @Test
    fun getUserFollowsTest() {
        // given
        val dummyUserFollows: List<UserFollow> = listOf(dummyUserFollow)
        val dummyUserFollowResponses: List<UserFollowResponse> = listOf(dummyUserFollowResponse)
        val queryFilter = UserFollowQueryFilter(
            followerId = null,
            followingId = dummyUser.id!!
        )
        val pagination = Pagination(
            page = 1,
            size = 10
        )
        val orderTypes: List<UserFollowOrderType> = emptyList()
        `when`(
            userFollowReader.searchUserFollows(
                queryFilter = queryFilter,
                pagination = pagination,
                orderTypes = orderTypes
            )
        ).thenReturn(dummyUserFollows)
        `when`(
            userFollowReader.searchUserFollowsCount(
                queryFilter = queryFilter,
            )
        ).thenReturn(dummyUserFollows.size.toLong())
        `when`(userFollowConverter.toResponseInBatch(userFollows = dummyUserFollows)).thenReturn(dummyUserFollowResponses)

        // when
        val result: PaginationResponse = service.getUserFollows(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )

        // then
        assertThat(result.page).isEqualTo(pagination.page)
        assertThat(result.size).isEqualTo(pagination.size)
        assertThat(result.data).isEqualTo(dummyUserFollowResponses)
        assertThat(result.totalCount).isEqualTo(dummyUserFollowResponses.size.toLong())
    }
}
