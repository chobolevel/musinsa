package com.musinsa.post

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.post.assembler.PostAssembler
import com.musinsa.post.converter.PostConverter
import com.musinsa.post.dto.CreatePostRequest
import com.musinsa.post.dto.PostResponse
import com.musinsa.post.dto.UpdatePostRequest
import com.musinsa.post.entity.Post
import com.musinsa.post.reader.PostQueryFilter
import com.musinsa.post.reader.PostReader
import com.musinsa.post.service.impl.PostServiceV1
import com.musinsa.post.store.PostStore
import com.musinsa.post.updater.PostUpdater
import com.musinsa.post.validator.PostBusinessValidator
import com.musinsa.post.vo.PostOrderType
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

    private val dummyPostResponse: PostResponse = DummyPost.toResponse()

    @Mock
    private lateinit var postConverter: PostConverter

    @Mock
    private lateinit var userRepositoryFacade: UserRepositoryFacade

    @Mock
    private lateinit var postStore: PostStore

    @Mock
    private lateinit var postReader: PostReader

    @Mock
    private lateinit var postAssembler: PostAssembler

    @Mock
    private lateinit var postUpdater: PostUpdater

    @Mock
    private lateinit var postBusinessValidator: PostBusinessValidator

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
        `when`(postStore.save(post = dummyPost)).thenReturn(dummyPost)

        // when
        val result: Long = postService.createPost(userId = dummyUserId, request = createRequest)

        // then
        assertThat(result).isEqualTo(dummyPost.id)
    }

    @Test
    fun getPostsTest() {
        // given
        val dummyPosts: List<Post> = listOf(dummyPost)
        val dummyPostResponses: List<PostResponse> = listOf(dummyPostResponse)
        val queryFilter = PostQueryFilter(
            userId = null,
            keyword = null
        )
        val pagination = Pagination(
            page = 1,
            size = 20
        )
        val orderTypes: List<PostOrderType> = emptyList()
        `when`(
            postReader.searchPosts(
                queryFilter = queryFilter,
                pagination = pagination,
                orderTypes = orderTypes
            )
        ).thenReturn(dummyPosts)
        `when`(
            postReader.searchPostsCount(
                queryFilter = queryFilter
            )
        ).thenReturn(dummyPosts.size.toLong())
        `when`(postConverter.toResponseInBatch(posts = dummyPosts)).thenReturn(dummyPostResponses)

        // when
        val result: PaginationResponse = postService.getPosts(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )

        // then
        assertThat(result.data).isEqualTo(dummyPostResponses)
        assertThat(result.totalCount).isEqualTo(dummyPostResponses.size.toLong())
        assertThat(result.page).isEqualTo(pagination.page)
        assertThat(result.size).isEqualTo(pagination.size)
    }

    @Test
    fun getPostTest() {
        // given
        val dummyPostId: Long = dummyPost.id!!
        `when`(postReader.findById(id = dummyPostId)).thenReturn(dummyPost)
        `when`(postConverter.toResponse(post = dummyPost)).thenReturn(dummyPostResponse)

        // when
        val result: PostResponse = postService.getPost(postId = dummyPostId)

        // then
        assertThat(result).isEqualTo(dummyPostResponse)
    }

    @Test
    fun updatePostTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyPostId: Long = dummyPost.id!!
        val updateRequest: UpdatePostRequest = DummyPost.toUpdateRequest()
        `when`(postReader.findById(id = dummyPostId)).thenReturn(dummyPost)
        `when`(
            postUpdater.markAsUpdate(
                request = updateRequest,
                post = dummyPost
            )
        ).thenReturn(dummyPost)

        // when
        val result: Long = postService.updatePost(
            userId = dummyUserId,
            postId = dummyPostId,
            request = updateRequest
        )

        // then
        assertThat(result).isEqualTo(dummyPostId)
    }

    @Test
    fun deletePostTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyPostId: Long = dummyPost.id!!
        `when`(postReader.findById(id = dummyPostId)).thenReturn(dummyPost)

        // when
        val result: Boolean = postService.deletePost(
            userId = dummyUserId,
            postId = dummyPostId
        )

        // then
        assertThat(result).isTrue()
    }
}
