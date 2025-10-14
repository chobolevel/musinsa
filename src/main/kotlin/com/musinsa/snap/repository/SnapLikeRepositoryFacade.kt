package com.musinsa.snap.repository

import org.springframework.stereotype.Component

@Component
class SnapLikeRepositoryFacade(
    private val repository: SnapLikeRepository,
    private val customRepository: SnapLikeCustomRepository
) {

    fun deleteById(id: Long) {
        repository.deleteById(id)
    }
}
