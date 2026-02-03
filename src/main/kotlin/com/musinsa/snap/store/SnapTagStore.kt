package com.musinsa.snap.store

import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.repository.SnapTagRepository
import org.springframework.stereotype.Component

@Component
class SnapTagStore(
    private val snapTagRepository: SnapTagRepository
) {

    fun save(snapTag: SnapTag): SnapTag {
        return snapTagRepository.save(snapTag)
    }
}
