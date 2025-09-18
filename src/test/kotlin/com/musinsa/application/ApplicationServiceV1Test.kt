package com.musinsa.application

import com.musinsa.application.converter.ApplicationConverter
import com.musinsa.application.dto.ApplicationResponse
import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.entity.Application
import com.musinsa.application.entity.ApplicationQueryFilter
import com.musinsa.application.entity.ApplicationRepositoryFacade
import com.musinsa.application.service.impl.ApplicationServiceV1
import com.musinsa.application.vo.ApplicationOrderType
import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
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

@DisplayName("application service v1 unit test")
@ExtendWith(MockitoExtension::class)
class ApplicationServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()

    private val dummyApplication: Application = DummyApplication.toEntity()
    private val dummyApplicationResponse: ApplicationResponse = DummyApplication.toResponse()

    @Mock
    private lateinit var repository: ApplicationRepositoryFacade

    @Mock
    private lateinit var userRepository: UserRepositoryFacade

    @Mock
    private lateinit var converter: ApplicationConverter

    @InjectMocks
    private lateinit var service: ApplicationServiceV1

    @Test
    fun createUserTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyCreateRequest: CreateApplicationRequest = DummyApplication.toCreateRequest()
        `when`(userRepository.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(converter.toEntity(request = dummyCreateRequest)).thenReturn(dummyApplication)
        `when`(repository.save(application = dummyApplication)).thenReturn(dummyApplication)

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
            repository.searchApplications(
                queryFilter = queryFilter,
                pagination = pagination,
                orderTypes = orderTypes
            )
        ).thenReturn(dummyApplications)
        `when`(
            repository.searchApplicationsCount(
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
        `when`(repository.findById(id = dummyApplicationId)).thenReturn(dummyApplication)
        `when`(converter.toResponse(entity = dummyApplication)).thenReturn(dummyApplicationResponse)

        // when
        val result: ApplicationResponse = service.getApplication(userId = dummyUserId, applicationId = dummyApplicationId)

        // then
        assertThat(result.id).isEqualTo(dummyApplication.id)
    }
}
