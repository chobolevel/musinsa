package com.musinsa.post.assembler

import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostImage
import com.musinsa.post.entity.PostTag
import com.musinsa.user.entity.User
import org.springframework.stereotype.Component

@Component
class PostAssembler {

    fun assemble(
        post: Post,
        user: User,
        postTags: List<PostTag>,
        postImages: List<PostImage>?
    ): Post {
        post.assignUser(user = user)
        post.addPostTagInBatch(postTags = postTags)
        postImages?.let {
            postImages.forEach { post.addPostImage(it) }
        }
        return post
    }
}
