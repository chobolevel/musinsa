package com.musinsa.snap.repository

import com.musinsa.snap.entity.Snap
import org.springframework.data.jpa.repository.JpaRepository

interface SnapRepository : JpaRepository<Snap, Long> {

    fun findByIdAndIsDeletedFalse(id: Long): Snap?
}
