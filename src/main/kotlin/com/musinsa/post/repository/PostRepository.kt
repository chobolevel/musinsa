package com.musinsa.post.repository

import com.musinsa.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): Post?
}
