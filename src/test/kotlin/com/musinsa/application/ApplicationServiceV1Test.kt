package com.musinsa.application

import com.musinsa.application.converter.ApplicationConverter
import com.musinsa.application.dto.AddApplicationMemberRequest
import com.musinsa.application.dto.ApplicationResponse
import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.dto.UpdateApplicationRequest
import com.musinsa.application.entity.Application
import com.musinsa.application.reader.ApplicationQueryFilter
import com.musinsa.application.reader.ApplicationReader
import com.musinsa.application.service.impl.ApplicationServiceV1
import com.musinsa.application.store.ApplicationStore
import com.musinsa.application.updater.ApplicationUpdater
import com.musinsa.application.vo.ApplicationOrderType
import com.musinsa.application.vo.member.ApplicationMemberType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import com.musinsa.user.reader.UserReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@DisplayName("application service v1 unit test")
@ExtendWith(MockitoExtension::class)
class ApplicationServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()
    private val dummySocialUser: User = DummyUser.toSocialUserEntity()

    private val dummyApplication: Application = DummyApplication.toEntity()
    private val dummyApplicationResponse: ApplicationResponse = DummyApplication.toResponse()

    @Mock
    private lateinit var applicationStore: ApplicationStore

    @Mock
    private lateinit var applicationReader: ApplicationReader

    @Mock
    private lateinit var userReader: UserReader

    @Mock
    private lateinit var converter: ApplicationConverter

    @Mock
    private lateinit var updater: ApplicationUpdater

    @InjectMocks
    private lateinit var service: ApplicationServiceV1

    @Test
    fun createUserTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyCreateRequest: CreateApplicationRequest = DummyApplication.toCreateRequest()
        `when`(userReader.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(converter.toEntity(request = dummyCreateRequest)).thenReturn(dummyApplication)
        `when`(applicationStore.save(application = dummyApplication)).thenReturn(dummyApplication)

        // when
        val result: Long = service.createApplication(userId = dummyUserId, request = dummyCreateRequest)

        // then
        assertThat(result).isEqualTo(dummyApplication.id)
    }

    @Test
    fun getApplicationsTest() {
        // given
        val queryFilter = ApplicationQueryFilter(
            name = null
        )
        val pagination = Pagination(
            page = 1,
            size = 20
        )
        val orderTypes: List<ApplicationOrderType> = emptyList()
        val dummyApplications: List<Application> = listOf(dummyApplication)
        val dummyApplicationResponses: List<ApplicationResponse> = listOf(dummyApplicationResponse)
        `when`(
            applicationReader.searchApplications(
                queryFilter = queryFilter,
                pagination = pagination,
                orderTypes = orderTypes
            )
        ).thenReturn(dummyApplications)
        `when`(
            applicationReader.searchApplicationsCount(
                queryFilter = queryFilter,
            )
        ).thenReturn(dummyApplications.size.toLong())
        `when`(converter.toResponseInBatch(entities = dummyApplications)).thenReturn(dummyApplicationResponses)

        // when
        val result: PaginationResponse = service.getApplications(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )

        // then
        assertThat(result.page).isEqualTo(pagination.page)
        assertThat(result.size).isEqualTo(pagination.size)
        assertThat(result.data).isEqualTo(dummyApplicationResponses)
        assertThat(result.totalCount).isEqualTo(dummyApplicationResponses.size.toLong())
    }

    @Test
    fun getApplicationTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyApplicationId: Long = dummyApplication.id!!
        `when`(applicationReader.findById(id = dummyApplicationId)).thenReturn(dummyApplication)
        `when`(converter.toResponse(entity = dummyApplication)).thenReturn(dummyApplicationResponse)

        // when
        val result: ApplicationResponse = service.getApplication(userId = dummyUserId, applicationId = dummyApplicationId)

        // then
        assertThat(result.id).isEqualTo(dummyApplication.id)
    }

    @Test
    fun updateApplicationTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyApplicationId: Long = dummyApplication.id!!
        val request: UpdateApplicationRequest = DummyApplication.toUpdateRequest()
        `when`(applicationReader.findById(id = dummyApplicationId)).thenReturn(dummyApplication)
        `when`(updater.markAsUpdate(entity = dummyApplication, request = request)).thenReturn(dummyApplication)

        // when
        val result: Long = service.updateApplication(userId = dummyUserId, applicationId = dummyApplicationId, request = request)

        // then
        assertThat(result).isEqualTo(dummyApplication.id)
    }

    @Test
    fun deleteApplicationTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyApplicationId: Long = dummyApplication.id!!
        `when`(applicationReader.findById(id = dummyApplicationId)).thenReturn(dummyApplication)

        // when
        val result: Boolean = service.deleteApplication(userId = dummyUserId, applicationId = dummyApplicationId)

        // then
        assertThat(result).isTrue()
    }

    @Test
    fun addMemberTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyApplicationId: Long = dummyApplication.id!!
        val dummyRequest = AddApplicationMemberRequest(
            memberId = dummySocialUser.id!!,
            memberType = ApplicationMemberType.MEMBER,
        )
        dummyApplication.addMember(
            user = dummyUser,
            memberType = ApplicationMemberType.OWNER,
        )
        `when`(applicationReader.findById(id = dummyApplicationId)).thenReturn(dummyApplication)
        `when`(userReader.findById(id = dummyRequest.memberId)).thenReturn(dummySocialUser)

        // when
        val result: Boolean = service.addMember(
            userId = dummyUserId,
            applicationId = dummyApplicationId,
            request = dummyRequest
        )

        // then
        assertThat(result).isTrue()
    }

    @Test
    fun removeMemberTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyApplicationId: Long = dummyApplication.id!!
        val dummyApplicationMemberId: Long = dummySocialUser.id!!
        dummyApplication.addMember(
            user = dummyUser,
            memberType = ApplicationMemberType.OWNER,
        )
        `when`(applicationReader.findById(id = dummyApplicationId)).thenReturn(dummyApplication)
        `when`(userReader.findById(id = dummyApplicationMemberId)).thenReturn(dummySocialUser)

        // when
        val result: Boolean = service.removeMember(
            userId = dummyUserId,
            applicationId = dummyApplicationId,
            applicationMemberId = dummyApplicationMemberId
        )

        // then
        assertThat(result).isTrue()
    }
}
