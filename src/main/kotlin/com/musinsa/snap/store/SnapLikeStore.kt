package com.musinsa.snap.store

import com.musinsa.snap.entity.SnapLike
import com.musinsa.snap.repository.SnapLikeRepository
import org.springframework.stereotype.Component

@Component
class SnapLikeStore(
    private val snapLikeRepository: SnapLikeRepository,
) {

    fun save(snapLike: SnapLike): SnapLike {
        return snapLikeRepository.save(snapLike)
    }

    fun deleteBySnapIdAndUserId(snapId: Long, userId: Long) {
        snapLikeRepository.deleteBySnapIdAndUserId(
            snapId = snapId,
            userId = userId
        )
    }
}
