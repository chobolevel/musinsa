package com.musinsa.snap.repository

import com.musinsa.snap.entity.SnapComment
import org.springframework.data.jpa.repository.JpaRepository

interface SnapCommentRepository : JpaRepository<SnapComment, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): SnapComment?
}
