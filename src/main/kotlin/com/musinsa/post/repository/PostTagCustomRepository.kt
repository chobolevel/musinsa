package com.musinsa.post.repository

import com.musinsa.post.entity.PostTag
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class PostTagCustomRepository : QuerydslRepositorySupport(PostTag::class.java)
