package com.musinsa.post.updater

import com.musinsa.post.dto.UpdatePostRequest
import com.musinsa.post.entity.Post
import com.musinsa.post.vo.PostUpdateMask
import org.springframework.stereotype.Component

@Component
class PostUpdater {

    fun markAsUpdate(
        request: UpdatePostRequest,
        post: Post
    ): Post {
        request.updateMask.forEach {
            when (it) {
                PostUpdateMask.TITLE -> post.title = request.title!!
                PostUpdateMask.CONTENT -> post.content = request.content!!
            }
        }
        return post
    }
}
