package com.musinsa.snap

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.converter.SnapLikeConverter
import com.musinsa.snap.dto.SnapLikeResponse
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapLike
import com.musinsa.snap.repository.SnapLikeQueryFilter
import com.musinsa.snap.repository.SnapLikeRepositoryFacade
import com.musinsa.snap.repository.SnapRepositoryFacade
import com.musinsa.snap.service.impl.SnapLikeServiceV1
import com.musinsa.snap.vo.SnapLikeOrderType
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

@DisplayName("snap like service v1 unit test")
@ExtendWith(MockitoExtension::class)
class SnapLikeServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()

    private val dummySnap: Snap = DummySnap.toEntity()

    private val dummySnapLike: SnapLike = DummySnapLike.toEntity()

    private val dummySnapLikeResponse: SnapLikeResponse = DummySnapLike.toResponse()

    @Mock
    private lateinit var repository: SnapLikeRepositoryFacade

    @Mock
    private lateinit var converter: SnapLikeConverter

    @Mock
    private lateinit var userRepository: UserRepositoryFacade

    @Mock
    private lateinit var snapRepository: SnapRepositoryFacade

    @InjectMocks
    private lateinit var service: SnapLikeServiceV1

    @Test
    fun getSnapLikesTest() {
        // given
        val dummyQueryFilter = SnapLikeQueryFilter(
            snapId = null,
            userId = null
        )
        val dummyPagination = Pagination(
            page = 1,
            size = 20
        )
        val dummyOrderTypes: List<SnapLikeOrderType> = emptyList()
        val dummySnapLikes: List<SnapLike> = listOf(dummySnapLike)
        val dummySnapLikeResponses: List<SnapLikeResponse> = listOf(dummySnapLikeResponse)
        `when`(
            repository.searchSnapLikes(
                queryFilter = dummyQueryFilter,
                pagination = dummyPagination,
                orderTypes = dummyOrderTypes
            )
        ).thenReturn(dummySnapLikes)
        `when`(
            repository.searchSnapLikesCount(
                queryFilter = dummyQueryFilter,
            )
        ).thenReturn(dummySnapLikes.size.toLong())
        `when`(converter.toResponseInBatch(snapLikes = dummySnapLikes)).thenReturn(dummySnapLikeResponses)

        // when
        val result: PaginationResponse = service.getSnapLikes(
            queryFilter = dummyQueryFilter,
            pagination = dummyPagination,
            orderTypes = dummyOrderTypes
        )

        // then
        assertThat(result.page).isEqualTo(dummyPagination.page)
        assertThat(result.size).isEqualTo(dummyPagination.size)
        assertThat(result.data).isEqualTo(dummySnapLikeResponses)
        assertThat(result.totalCount).isEqualTo(dummySnapLikeResponses.size.toLong())
    }
}
