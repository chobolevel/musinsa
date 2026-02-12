package com.musinsa.post

import com.musinsa.post.dto.CreatePostRequest
import com.musinsa.post.dto.PostResponse
import com.musinsa.post.dto.UpdatePostRequest
import com.musinsa.post.entity.Post
import com.musinsa.post.image.DummyPostImage
import com.musinsa.post.tag.DummyPostTag
import com.musinsa.post.vo.PostUpdateMask
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User

object DummyPost {
    private const val id: Long = 1L
    private const val title: String = "게시글 제목입니다"
    private const val content: String = "게시글 내용입니다. 어떤 내용을 작성해볼까요? 기술 블로그로 사용해볼까요?"
    private const val createdAt: Long = 0L
    private const val updatedAt: Long = 0L

    private val dummyPost: Post by lazy {
        Post(
            title = title,
            content = content
        ).also { it.id = id }
    }

    private val dummyPostResponse: PostResponse by lazy {
        val user: User = DummyUser.toEntity()
        PostResponse(
            id = id,
            userId = user.id!!,
            userName = user.name,
            title = title,
            content = content,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    private val dummyCreateRequest: CreatePostRequest by lazy {
        CreatePostRequest(
            title = title,
            content = content,
            postTagIds = setOf(DummyPostTag.toEntity().id!!),
            postImages = listOf(DummyPostImage.toCreateRequest())
        )
    }

    private val dummyUpdateRequest: UpdatePostRequest by lazy {
        UpdatePostRequest(
            postTagIds = null,
            title = "변경할 게시글 제목",
            content = null,
            postImages = null,
            updateMask = listOf(PostUpdateMask.TITLE)
        )
    }

    fun toEntity(): Post = dummyPost

    fun toResponse(): PostResponse = dummyPostResponse

    fun toCreateRequest(): CreatePostRequest = dummyCreateRequest

    fun toUpdateRequest(): UpdatePostRequest = dummyUpdateRequest
}
