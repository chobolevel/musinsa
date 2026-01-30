package com.musinsa.post.service.impl

import com.musinsa.post.assembler.PostAssembler
import com.musinsa.post.converter.PostConverter
import com.musinsa.post.dto.CreatePostRequest
import com.musinsa.post.entity.Post
import com.musinsa.post.service.PostService
import com.musinsa.post.store.PostStore
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceV1(
    private val postConverter: PostConverter,
    private val userRepositoryFacade: UserRepositoryFacade,
    private val postStore: PostStore,
    private val postAssembler: PostAssembler
) : PostService {

    @Transactional
    override fun createPost(userId: Long, request: CreatePostRequest): Long {
        val basePost: Post = postConverter.toEntity(request = request)
        val user: User = userRepositoryFacade.findById(id = userId)

        val post: Post = postAssembler.assemble(
            post = basePost,
            user = user
        )

        return postStore.save(post = post).id!!
    }
}
