package com.musinsa.application

import com.musinsa.application.converter.ApplicationConverter
import com.musinsa.application.dto.CreateApplicationRequest
import com.musinsa.application.entity.Application
import com.musinsa.application.entity.ApplicationRepositoryFacade
import com.musinsa.application.service.impl.ApplicationServiceV1
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import org.assertj.core.api.Assertions
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
        Assertions.assertThat(result).isEqualTo(dummyApplication.id)
    }
}
