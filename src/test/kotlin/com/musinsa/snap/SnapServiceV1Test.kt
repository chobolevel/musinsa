package com.musinsa.snap

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.assembler.SnapAssembler
import com.musinsa.snap.converter.SnapConverter
import com.musinsa.snap.converter.SnapImageConverter
import com.musinsa.snap.dto.CreateSnapRequest
import com.musinsa.snap.dto.SnapResponse
import com.musinsa.snap.dto.UpdateSnapRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapImage
import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.image.DummySnapImage
import com.musinsa.snap.repository.SnapQueryFilter
import com.musinsa.snap.repository.SnapRepositoryFacade
import com.musinsa.snap.repository.SnapTagRepositoryFacade
import com.musinsa.snap.service.impl.SnapServiceV1
import com.musinsa.snap.tag.DummySnapTag
import com.musinsa.snap.updater.SnapUpdater
import com.musinsa.snap.validator.SnapBusinessValidator
import com.musinsa.snap.vo.SnapOrderType
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

    private val dummySnapTag: SnapTag = DummySnapTag.toEntity()

    private val dummySnapImage: SnapImage = DummySnapImage.toEntity()

    private val dummySnapResponse: SnapResponse = DummySnap.toResponse()

    @Mock
    private lateinit var converter: SnapConverter

    @Mock
    private lateinit var snapImageConverter: SnapImageConverter

    @Mock
    private lateinit var userRepository: UserRepositoryFacade

    @Mock
    private lateinit var snapRepository: SnapRepositoryFacade

    @Mock
    private lateinit var snapTagRepository: SnapTagRepositoryFacade

    @Mock
    private lateinit var assembler: SnapAssembler

    @Mock
    private lateinit var updater: SnapUpdater

    @Mock
    private lateinit var validator: SnapBusinessValidator

    @InjectMocks
    private lateinit var service: SnapServiceV1

    @Test
    fun createSnapTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyRequest: CreateSnapRequest = DummySnap.toCreateRequest()
        val dummySnapTags: List<SnapTag> = listOf(dummySnapTag)
        val dummySnapImages: List<SnapImage> = listOf(dummySnapImage)
        `when`(converter.toEntity(request = dummyRequest)).thenReturn(dummySnap)
        `when`(snapImageConverter.toEntityInBatch(requests = dummyRequest.snapImages)).thenReturn(dummySnapImages)
        `when`(userRepository.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(snapTagRepository.findByIds(ids = dummyRequest.snapTagIds)).thenReturn(dummySnapTags)
        `when`(
            assembler.assemble(
                snap = dummySnap,
                writer = dummyUser,
                snapTags = dummySnapTags,
                snapImages = dummySnapImages
            )
        ).thenReturn(dummySnap)
        `when`(snapRepository.save(snap = dummySnap)).thenReturn(dummySnap)

        // when
        val result: Long = service.createSnap(userId = dummyUserId, request = dummyRequest)

        // then
        assertThat(result).isEqualTo(dummySnap.id)
    }

    @Test
    fun getSnapsTest() {
        // given
        val dummyQueryFilter = SnapQueryFilter(
            writerId = null,
            keyword = null
        )
        val dummyPagination = Pagination(
            page = 1,
            size = 10
        )
        val dummyOrderTypes: List<SnapOrderType> = emptyList()
        val dummySnaps: List<Snap> = listOf(dummySnap)
        val dummySnapResponses: List<SnapResponse> = listOf(dummySnapResponse)
        `when`(
            snapRepository.searchSnaps(
                queryFilter = dummyQueryFilter,
                pagination = dummyPagination,
                orderTypes = dummyOrderTypes
            )
        ).thenReturn(dummySnaps)
        `when`(
            snapRepository.searchSnapsCount(
                queryFilter = dummyQueryFilter,
            )
        ).thenReturn(dummySnaps.size.toLong())
        `when`(converter.toResponseInBatch(snaps = dummySnaps)).thenReturn(dummySnapResponses)

        // when
        val result: PaginationResponse = service.getSnaps(
            queryFilter = dummyQueryFilter,
            pagination = dummyPagination,
            orderTypes = dummyOrderTypes
        )

        // then
        assertThat(result.page).isEqualTo(dummyPagination.page)
        assertThat(result.size).isEqualTo(dummyPagination.size)
        assertThat(result.data).isEqualTo(dummySnapResponses)
        assertThat(result.totalCount).isEqualTo(dummySnapResponses.size.toLong())
    }

    @Test
    fun getSnapTest() {
        // given
        val dummySnapId: Long = dummySnap.id!!
        `when`(snapRepository.findById(id = dummySnapId)).thenReturn(dummySnap)
        `when`(converter.toResponse(snap = dummySnap)).thenReturn(dummySnapResponse)

        // when
        val result: SnapResponse = service.getSnap(id = dummySnapId)

        // then
        assertThat(result.id).isEqualTo(dummySnapResponse.id)
    }

    @Test
    fun updateSnapTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummySnapId: Long = dummySnap.id!!
        val dummyRequest: UpdateSnapRequest = DummySnap.toUpdateRequest()
        dummySnap.assignWriter(user = dummyUser)
        `when`(snapRepository.findById(id = dummySnapId)).thenReturn(dummySnap)
        `when`(
            updater.markAsUpdate(
                request = dummyRequest,
                snap = dummySnap
            )
        ).thenReturn(dummySnap)

        // when
        val result: Long = service.updateSnap(
            userId = dummyUserId,
            snapId = dummySnapId,
            request = dummyRequest
        )

        // then
        assertThat(result).isEqualTo(dummySnap.id)
    }

    @Test
    fun deleteSnapTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummySnapId: Long = dummySnap.id!!
        dummySnap.assignWriter(user = dummyUser)
        `when`(snapRepository.findById(id = dummySnapId)).thenReturn(dummySnap)

        // when
        val result: Boolean = service.deleteSnap(
            userId = dummyUserId,
            snapId = dummySnapId
        )

        // then
        assertThat(result).isTrue()
    }
}
