package com.musinsa.snap.repository

import com.musinsa.snap.entity.SnapTag
import org.springframework.data.jpa.repository.JpaRepository

interface SnapTagRepository : JpaRepository<SnapTag, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): SnapTag?

    fun findInIdsAndIsDeletedFalse(ids: List<Long>): List<SnapTag>
}
