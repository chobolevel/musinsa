package com.musinsa.snap.repository

import org.springframework.data.jpa.repository.JpaRepository

interface SnapTagRepository : JpaRepository<SnapTag, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): SnapTag?
}
