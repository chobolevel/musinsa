package com.musinsa.post.repository

import com.musinsa.post.entity.Post
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class PostCustomRepository: QuerydslRepositorySupport(Post::class.java) {
}
