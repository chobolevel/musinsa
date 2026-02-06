package com.musinsa.post.repository

import com.musinsa.post.entity.PostTag
import org.springframework.data.jpa.repository.JpaRepository

interface PostTagRepository : JpaRepository<PostTag, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): PostTag?

    // DTO에서는 중복 방지를 위해 Set을 사용하더라 JpaRepository에서는 Collection, List로 받는 것이 일반적이기 때문에 표준 유지
    fun findByIdInAndIsDeletedFalse(ids: List<Long>): List<PostTag>
}
