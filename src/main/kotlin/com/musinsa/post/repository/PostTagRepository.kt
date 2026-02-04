package com.musinsa.post.repository

import com.musinsa.post.entity.PostTag
import org.springframework.data.jpa.repository.JpaRepository

interface PostTagRepository : JpaRepository<PostTag, Long>
