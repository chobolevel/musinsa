package com.musinsa.snap.repository

import com.musinsa.snap.entity.SnapLike
import org.springframework.data.jpa.repository.JpaRepository

interface SnapLikeRepository : JpaRepository<SnapLike, Long>
