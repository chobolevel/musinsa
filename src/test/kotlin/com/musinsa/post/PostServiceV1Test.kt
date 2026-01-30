package com.musinsa.post

import com.musinsa.post.assembler.PostAssembler
import com.musinsa.post.converter.PostConverter
import com.musinsa.post.dto.CreatePostRequest
import com.musinsa.post.entity.Post
import com.musinsa.post.repository.PostRepositoryFacade
import com.musinsa.post.service.impl.PostServiceV1
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

@DisplayName("post service v1 unit test")
@ExtendWith(MockitoExtension::class)
class PostServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()

    private val dummyPost: Post = DummyPost.toEntity()

    @Mock
    private lateinit var postConverter: PostConverter

    @Mock
    private lateinit var userRepositoryFacade: UserRepositoryFacade

    @Mock
    private lateinit var postRepositoryFacade: PostRepositoryFacade

    @Mock
    private lateinit var postAssembler: PostAssembler

    @InjectMocks
    private lateinit var postService: PostServiceV1

    @Test
    fun createPostTest() {
        //
        val dummyUserId: Long = dummyUser.id!!
        val createRequest: CreatePostRequest = DummyPost.toCreateRequest()
        `when`(userRepositoryFacade.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(postConverter.toEntity(request = createRequest)).thenReturn(dummyPost)
        `when`(
            postAssembler.assemble(
                post = dummyPost,
                user = dummyUser
            )
        ).thenReturn(dummyPost)
        `when`(postRepositoryFacade.save(post = dummyPost)).thenReturn(dummyPost)

        // when
        val result: Long = postService.createPost(userId = dummyUserId, request = createRequest)

        // then
        assertThat(result).isEqualTo(dummyPost.id)
    }
}
