package com.musinsa.post.assembler

import com.musinsa.post.entity.Post
import com.musinsa.user.entity.User
import org.springframework.stereotype.Component

@Component
class PostAssembler {

    fun assemble(
        post: Post,
        user: User
    ): Post {
        post.assignUser(user = user)
        return post
    }
}
