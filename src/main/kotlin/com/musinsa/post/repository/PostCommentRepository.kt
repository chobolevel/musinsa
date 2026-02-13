package com.musinsa.post.repository

import com.musinsa.post.entity.PostComment
import org.springframework.data.jpa.repository.JpaRepository

interface PostCommentRepository : JpaRepository<PostComment, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): PostComment?
}
