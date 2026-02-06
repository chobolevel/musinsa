package com.musinsa.post.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.post.assembler.PostAssembler
import com.musinsa.post.converter.PostConverter
import com.musinsa.post.dto.CreatePostRequest
import com.musinsa.post.dto.PostResponse
import com.musinsa.post.dto.UpdatePostRequest
import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostTag
import com.musinsa.post.reader.PostQueryFilter
import com.musinsa.post.reader.PostReader
import com.musinsa.post.reader.PostTagReader
import com.musinsa.post.service.PostService
import com.musinsa.post.store.PostStore
import com.musinsa.post.updater.PostUpdater
import com.musinsa.post.validator.PostBusinessValidator
import com.musinsa.post.vo.PostOrderType
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceV1(
    private val postConverter: PostConverter,
    private val userRepositoryFacade: UserRepositoryFacade,
    private val postStore: PostStore,
    private val postReader: PostReader,
    private val postTagReader: PostTagReader,
    private val postAssembler: PostAssembler,
    private val postUpdater: PostUpdater,
    private val postBusinessValidator: PostBusinessValidator,
) : PostService {

    @Transactional
    override fun createPost(userId: Long, request: CreatePostRequest): Long {
        val basePost: Post = postConverter.toEntity(request = request)
        val user: User = userRepositoryFacade.findById(id = userId)
        val postTags: List<PostTag> = postTagReader.findByIds(ids = request.postTagIds.toList())

        val post: Post = postAssembler.assemble(
            post = basePost,
            user = user,
            postTags = postTags
        )

        return postStore.save(post = post).id!!
    }

    @Transactional(readOnly = true)
    override fun getPosts(
        queryFilter: PostQueryFilter,
        pagination: Pagination,
        orderTypes: List<PostOrderType>
    ): PaginationResponse {
        val posts: List<Post> = postReader.searchPosts(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes,
        )
        val totalCount: Long = postReader.searchPostsCount(
            queryFilter = queryFilter,
        )
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = postConverter.toResponseInBatch(posts = posts),
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    override fun getPost(postId: Long): PostResponse {
        val post: Post = postReader.findById(id = postId)
        return postConverter.toResponse(post = post)
    }

    @Transactional
    override fun updatePost(
        userId: Long,
        postId: Long,
        request: UpdatePostRequest
    ): Long {
        val post: Post = postReader.findById(id = postId)
        postBusinessValidator.validateWriter(
            userId = userId,
            post = post
        )
        postUpdater.markAsUpdate(
            request = request,
            post = post
        )
        return postId
    }

    @Transactional
    override fun deletePost(userId: Long, postId: Long): Boolean {
        val post: Post = postReader.findById(id = postId)
        postBusinessValidator.validateWriter(
            userId = userId,
            post = post
        )
        post.delete()
        return true
    }
}
