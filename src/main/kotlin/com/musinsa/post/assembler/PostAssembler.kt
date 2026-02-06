package com.musinsa.post.assembler

import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostTag
import com.musinsa.user.entity.User
import org.springframework.stereotype.Component

@Component
class PostAssembler {

    fun assemble(
        post: Post,
        user: User,
        postTags: List<PostTag>
    ): Post {
        post.assignUser(user = user)
        post.addPostTagInBatch(postTags = postTags)
        return post
    }
}
