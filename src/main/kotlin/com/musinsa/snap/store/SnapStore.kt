package com.musinsa.snap.store

import com.musinsa.snap.entity.Snap
import com.musinsa.snap.repository.SnapRepository
import org.springframework.stereotype.Component

@Component
class SnapStore(
    private val snapRepository: SnapRepository
) {

    fun save(snap: Snap): Snap {
        return snapRepository.save(snap)
    }
}
