package com.musinsa.snap.repository

import com.musinsa.snap.entity.Snap
import org.springframework.stereotype.Component

@Component
class SnapRepositoryFacade(
    private val repository: SnapRepository,
    private val customRepository: SnapCustomRepository
) {

    fun save(snap: Snap): Snap {
        return repository.save(snap)
    }
}
