package com.musinsa.post.service.impl

import com.musinsa.post.assembler.PostCommentAssembler
import com.musinsa.post.converter.PostCommentConverter
import com.musinsa.post.dto.CreatePostCommentRequest
import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostComment
import com.musinsa.post.reader.PostCommentReader
import com.musinsa.post.reader.PostReader
import com.musinsa.post.service.PostCommentService
import com.musinsa.post.store.PostCommentStore
import com.musinsa.user.entity.User
import com.musinsa.user.reader.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostCommentServiceV1(
    private val postCommentConverter: PostCommentConverter,
    private val postCommentStore: PostCommentStore,
    private val userReader: UserReader,
    private val postReader: PostReader,
    private val postCommentReader: PostCommentReader,
    private val postCommentAssembler: PostCommentAssembler
) : PostCommentService {

    @Transactional
    override fun createPostComment(
        userId: Long,
        postId: Long,
        request: CreatePostCommentRequest
    ): Long {
        val basePostComment: PostComment = postCommentConverter.toEntity(request = request)
        val user: User = userReader.findById(id = userId)
        val post: Post = postReader.findById(id = postId)
        val parentPostComment: PostComment? = request.parentId?.let { postCommentReader.findById(id = it) }

        val postComment: PostComment = postCommentAssembler.assemble(
            postComment = basePostComment,
            user = user,
            post = post,
            parentPostComment = parentPostComment
        )

        return postCommentStore.save(postComment = postComment).id!!
    }
}
