package com.musinsa.post.updater

import com.musinsa.post.dto.UpdatePostCommentRequest
import com.musinsa.post.entity.PostComment
import com.musinsa.post.vo.PostCommentUpdateMask
import org.springframework.stereotype.Component

@Component
class PostCommentUpdater {

    fun markAsUpdate(
        request: UpdatePostCommentRequest,
        postComment: PostComment
    ): PostComment {
        request.updateMask.forEach {
            when (it) {
                PostCommentUpdateMask.COMMENT -> postComment.comment = request.comment!!
            }
        }
        return postComment
    }
}
