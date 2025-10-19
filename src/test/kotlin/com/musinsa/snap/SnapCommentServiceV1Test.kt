package com.musinsa.snap

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.converter.SnapCommentConverter
import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.dto.SnapCommentResponse
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapComment
import com.musinsa.snap.repository.SnapCommentQueryFilter
import com.musinsa.snap.repository.SnapCommentRepositoryFacade
import com.musinsa.snap.repository.SnapRepositoryFacade
import com.musinsa.snap.service.impl.SnapCommentServiceV1
import com.musinsa.snap.vo.SnapCommentOrderType
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

    private val dummySnapCommentResponse: SnapCommentResponse = DummySnapComment.toResponse()

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

    @Test
    fun getSnapCommentsTest() {
        // given
        val dummyQueryFilter = SnapCommentQueryFilter(
            snapId = null,
            userId = null
        )
        val dummyPagination = Pagination(
            page = 1,
            size = 20
        )
        val dummyOrderTypes: List<SnapCommentOrderType> = emptyList()
        val dummySnapComments: List<SnapComment> = listOf(dummySnapComment)
        val dummySnapCommentResponses: List<SnapCommentResponse> = listOf(dummySnapCommentResponse)
        `when`(
            repository.searchSnapComments(
                queryFilter = dummyQueryFilter,
                pagination = dummyPagination,
                orderTypes = dummyOrderTypes
            )
        ).thenReturn(dummySnapComments)
        `when`(
            repository.searchSnapCommentsCount(
                queryFilter = dummyQueryFilter,
            )
        ).thenReturn(dummySnapComments.size.toLong())
        `when`(converter.toResponseInBatch(snapComments = dummySnapComments)).thenReturn(dummySnapCommentResponses)

        // when
        val result: PaginationResponse = service.getSnapComments(
            queryFilter = dummyQueryFilter,
            pagination = dummyPagination,
            orderTypes = dummyOrderTypes
        )

        // then
        assertThat(result.page).isEqualTo(dummyPagination.page)
        assertThat(result.size).isEqualTo(dummyPagination.size)
        assertThat(result.data).isEqualTo(dummySnapCommentResponses)
        assertThat(result.totalCount).isEqualTo(dummySnapCommentResponses.size.toLong())
    }
}
