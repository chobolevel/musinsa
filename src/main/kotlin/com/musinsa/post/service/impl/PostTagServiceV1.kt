package com.musinsa.post.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.post.converter.PostTagConverter
import com.musinsa.post.dto.CreatePostTagRequest
import com.musinsa.post.dto.PostTagResponse
import com.musinsa.post.dto.UpdatePostTagRequest
import com.musinsa.post.entity.PostTag
import com.musinsa.post.reader.PostTagQueryFilter
import com.musinsa.post.reader.PostTagReader
import com.musinsa.post.service.PostTagService
import com.musinsa.post.store.PostTagStore
import com.musinsa.post.updater.PostTagUpdater
import com.musinsa.post.vo.PostTagOrderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostTagServiceV1(
    private val postTagConverter: PostTagConverter,
    private val postTagStore: PostTagStore,
    private val postTagReader: PostTagReader,
    private val postTagUpdater: PostTagUpdater
) : PostTagService {

    @Transactional
    override fun createPostTag(request: CreatePostTagRequest): Long {
        val postTag: PostTag = postTagConverter.toEntity(request = request)
        return postTagStore.save(postTag = postTag).id!!
    }

    @Transactional(readOnly = true)
    override fun getPostTags(
        queryFilter: PostTagQueryFilter,
        pagination: Pagination,
        orderTypes: List<PostTagOrderType>
    ): PaginationResponse {
        val postTags: List<PostTag> = postTagReader.searchPostTags(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = postTagReader.searchPostTagsCount(queryFilter = queryFilter)
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = postTagConverter.toResponseInBatch(postTags = postTags),
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    override fun getPostTag(postTagId: Long): PostTagResponse {
        val postTag: PostTag = postTagReader.findById(id = postTagId)
        return postTagConverter.toResponse(postTag = postTag)
    }

    @Transactional
    override fun updatePostTag(
        postTagId: Long,
        request: UpdatePostTagRequest
    ): Long {
        val postTag: PostTag = postTagReader.findById(id = postTagId)
        postTagUpdater.markAsUpdate(
            request = request,
            postTag = postTag,
        )
        return postTagId
    }
}
