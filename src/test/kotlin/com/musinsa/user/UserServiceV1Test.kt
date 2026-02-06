package com.musinsa.user

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.user.converter.UserConverter
import com.musinsa.user.dto.ChangeUserPasswordRequest
import com.musinsa.user.dto.CreateUserRequest
import com.musinsa.user.dto.UpdateUserRequest
import com.musinsa.user.dto.UserResponse
import com.musinsa.user.entity.User
import com.musinsa.user.reader.UserQueryFilter
import com.musinsa.user.reader.UserReader
import com.musinsa.user.serivce.impl.UserServiceV1
import com.musinsa.user.store.UserStore
import com.musinsa.user.updater.UserUpdater
import com.musinsa.user.validator.UserBusinessValidator
import com.musinsa.user.vo.UserOrderType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
@DisplayName("user service v1 unit test")
class UserServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()
    private val dummySocialUser: User = DummyUser.toSocialUserEntity()

    private val dummyUserResponse: UserResponse = DummyUser.toResponse()

    @Mock
    private lateinit var converter: UserConverter

    @Mock
    private lateinit var userStore: UserStore

    @Mock
    private lateinit var userReader: UserReader

    @Mock
    private lateinit var validator: UserBusinessValidator

    @Mock
    private lateinit var updater: UserUpdater

    @Mock
    private lateinit var passwordEncoder: BCryptPasswordEncoder

    @InjectMocks
    private lateinit var userService: UserServiceV1

    @Test
    fun createUserTest() {
        // given
        val createRequest: CreateUserRequest = DummyUser.toCreateRequest()
        `when`(converter.toEntity(request = createRequest)).thenReturn(dummyUser)
        `when`(userStore.save(user = dummyUser)).thenReturn(dummyUser)

        // when
        val result: Long = userService.createUser(request = createRequest)

        // then
        assertThat(result).isEqualTo(dummyUser.id)
    }

    @Test
    fun getUsersTest() {
        // given
        val dummyUsers: List<User> = listOfNotNull(dummyUser)
        val dummyUserResponses: List<UserResponse> = listOfNotNull(dummyUserResponse)
        val queryFilter = UserQueryFilter(
            signUpType = null,
            email = null,
            name = null,
            phone = null,
            gender = null,
            status = null,
            grade = null,
            role = null,
        )
        val pagination = Pagination(
            page = 1,
            size = 10
        )
        val orderTypes: List<UserOrderType> = emptyList()
        `when`(
            userReader.searchUsers(
                queryFilter = queryFilter,
                pagination = pagination,
                orderTypes = orderTypes
            )
        ).thenReturn(dummyUsers)
        `when`(
            userReader.searchUsersCount(
                queryFilter = queryFilter,
            )
        ).thenReturn(dummyUsers.size.toLong())
        `when`(converter.toResponseInBatch(users = dummyUsers)).thenReturn(dummyUserResponses)

        // when
        val result: PaginationResponse = userService.getUsers(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )

        // then
        assertThat(result.page).isEqualTo(pagination.page)
        assertThat(result.size).isEqualTo(pagination.size)
        assertThat(result.data).isEqualTo(dummyUserResponses)
        assertThat(result.totalCount).isEqualTo(dummyUserResponses.size.toLong())
    }

    @Test
    fun getUserTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        `when`(userReader.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(converter.toResponse(user = dummyUser)).thenReturn(dummyUserResponse)

        // when
        val result: UserResponse = userService.getUser(id = dummyUserId)

        // then
        assertThat(result.id).isEqualTo(dummyUserId)
        assertThat(result.email).isEqualTo(dummyUserResponse.email)
    }

    @Test
    fun updateUserTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val updateRequest: UpdateUserRequest = DummyUser.toUpdateRequest()
        `when`(userReader.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(
            updater.markAsUpdate(
                request = updateRequest,
                user = dummyUser
            )
        ).thenReturn(dummyUser)

        // when
        val result: Long = userService.updateUser(
            userId = dummyUserId,
            request = updateRequest
        )

        // then
        assertThat(result).isEqualTo(dummyUserId)
    }

    @Test
    fun resignUserTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        `when`(userReader.findById(id = dummyUserId)).thenReturn(dummyUser)

        // when
        val result: Boolean = userService.resignUser(userId = dummyUserId)

        // then
        assertThat(result).isTrue()
    }

    @Test
    fun changePasswordTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val request: ChangeUserPasswordRequest = DummyUser.toChangePasswordRequest()
        `when`(userReader.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(passwordEncoder.encode(request.newPassword)).thenReturn(request.newPassword)

        // when
        val result: Long = userService.changePassword(userId = dummyUserId, request = request)

        // then
        assertThat(result).isEqualTo(dummyUserId)
    }
}
