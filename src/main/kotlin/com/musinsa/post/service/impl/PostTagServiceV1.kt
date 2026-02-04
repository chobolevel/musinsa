package com.musinsa.post.service.impl

import com.musinsa.post.converter.PostTagConverter
import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.entity.PostTag
import com.musinsa.post.service.PostTagService
import com.musinsa.post.store.PostTagStore
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostTagServiceV1(
    private val postTagConverter: PostTagConverter,
    private val postTagStore: PostTagStore,
) : PostTagService {

    @Transactional
    override fun createPostTag(request: CreatePostTagRequest): Long {
        val postTag: PostTag = postTagConverter.toEntity(request = request)
        return postTagStore.save(postTag = postTag).id!!
    }
}
