package com.musinsa.snap.entity

import org.springframework.data.jpa.repository.JpaRepository

interface SnapTagRepository : JpaRepository<SnapTag, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): SnapTag?
}
