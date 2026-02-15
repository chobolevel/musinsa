package com.musinsa.post.comment

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.post.DummyPost
import com.musinsa.post.assembler.PostCommentAssembler
import com.musinsa.post.converter.PostCommentConverter
import com.musinsa.post.dto.CreatePostCommentRequest
import com.musinsa.post.dto.PostCommentResponse
import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostComment
import com.musinsa.post.reader.PostCommentQueryFilter
import com.musinsa.post.reader.PostCommentReader
import com.musinsa.post.reader.PostReader
import com.musinsa.post.service.impl.PostCommentServiceV1
import com.musinsa.post.store.PostCommentStore
import com.musinsa.post.vo.PostCommentOrderType
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

@DisplayName("PostCommentServiceV1 unit test")
@ExtendWith(MockitoExtension::class)
class PostCommentServiceV1Test {

    private val dummyUser: User = DummyUser.toEntity()

    private val dummyPost: Post = DummyPost.toEntity()

    private val dummyPostComment: PostComment = DummyPostComment.toEntity()

    private val dummyPostCommentResponse: PostCommentResponse = DummyPostComment.toResponse()

    private val dummyParentPostComment: PostComment = DummyPostComment.toParentEntity()

    @Mock
    private lateinit var postCommentConverter: PostCommentConverter

    @Mock
    private lateinit var postCommentStore: PostCommentStore

    @Mock
    private lateinit var userReader: UserReader

    @Mock
    private lateinit var postReader: PostReader

    @Mock
    private lateinit var postCommentReader: PostCommentReader

    @Mock
    private lateinit var postCommentAssembler: PostCommentAssembler

    @InjectMocks
    private lateinit var postCommentService: PostCommentServiceV1

    @Test
    fun createPostCommentTest() {
        // given
        val dummyUserId: Long = dummyUser.id!!
        val dummyPostId: Long = dummyPost.id!!
        val createRequest: CreatePostCommentRequest = DummyPostComment.toCreateRequest()
        `when`(postCommentConverter.toEntity(request = createRequest)).thenReturn(dummyPostComment)
        `when`(userReader.findById(id = dummyUserId)).thenReturn(dummyUser)
        `when`(postReader.findById(id = dummyPostId)).thenReturn(dummyPost)
        `when`(postCommentReader.findById(id = createRequest.parentId!!)).thenReturn(dummyParentPostComment)
        `when`(
            postCommentAssembler.assemble(
                postComment = dummyPostComment,
                user = dummyUser,
                post = dummyPost,
                parentPostComment = dummyParentPostComment
            )
        ).thenReturn(dummyPostComment)
        `when`(postCommentStore.save(postComment = dummyPostComment)).thenReturn(dummyPostComment)

        // when
        val result: Long = postCommentService.createPostComment(
            userId = dummyUserId,
            postId = dummyPostId,
            request = createRequest
        )

        // then
        assertThat(result).isEqualTo(dummyPostComment.id)
    }

    @Test
    fun getPostCommentsTest() {
        // given
        val queryFilter = PostCommentQueryFilter(
            userId = null,
            postId = null,
            parentId = null
        )
        val pagination = Pagination(
            page = 1,
            size = 10
        )
        val orderTypes: List<PostCommentOrderType> = emptyList()
        val dummyPostComments: List<PostComment> = listOf(dummyPostComment)
        val dummyPostCommentResponses: List<PostCommentResponse> = listOf(dummyPostCommentResponse)
        `when`(
            postCommentReader.searchPostComments(
                queryFilter = queryFilter,
                pagination = pagination,
                orderTypes = orderTypes
            )
        ).thenReturn(dummyPostComments)
        `when`(
            postCommentReader.searchPostCommentsCount(
                queryFilter = queryFilter,
            )
        ).thenReturn(dummyPostComments.size.toLong())
        `when`(
            postCommentConverter.toResponseInBatch(
                postComments = dummyPostComments
            )
        ).thenReturn(dummyPostCommentResponses)

        // when
        val result: PaginationResponse = postCommentService.getPostComments(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )

        // then
        assertThat(result.page).isEqualTo(pagination.page)
        assertThat(result.size).isEqualTo(pagination.size)
        assertThat(result.data).isEqualTo(dummyPostCommentResponses)
        assertThat(result.totalCount).isEqualTo(dummyPostCommentResponses.size.toLong())
    }

    @Test
    fun getPostCommentTest() {
        // given
        val dummyPostCommentId: Long = dummyPostComment.id!!
        `when`(postCommentReader.findById(id = dummyPostCommentId)).thenReturn(dummyPostComment)
        `when`(postCommentConverter.toResponse(postComment = dummyPostComment)).thenReturn(dummyPostCommentResponse)

        // when
        val result: PostCommentResponse = postCommentService.getPostComment(
            postCommentId = dummyPostCommentId
        )

        // then
        assertThat(result.id).isEqualTo(dummyPostCommentId)
    }
}
