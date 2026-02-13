package com.musinsa.post.assembler

import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostComment
import com.musinsa.user.entity.User
import org.springframework.stereotype.Component

@Component
class PostCommentAssembler {

    fun assemble(
        postComment: PostComment,
        user: User,
        post: Post,
        parentPostComment: PostComment?
    ): PostComment {
        postComment.assignUser(user = user)
        postComment.assignPost(post = post)
        parentPostComment?.let { postComment.assignParent(parent = parentPostComment) }
        return postComment
    }
}
