package com.musinsa.snap.service.impl

import com.musinsa.snap.converter.SnapConverter
import com.musinsa.snap.dto.CreateSnapRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.repository.SnapRepositoryFacade
import com.musinsa.snap.service.SnapService
import com.musinsa.user.entity.UserRepositoryFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SnapServiceV1(
    private val converter: SnapConverter,
    private val userRepository: UserRepositoryFacade,
    private val snapRepository: SnapRepositoryFacade
) : SnapService {

    @Transactional
    override fun createSnap(userId: Long, request: CreateSnapRequest): Long {
        val snap: Snap = converter.toEntity(request = request).also {
            it.mapWriter(user = userRepository.findById(id = userId))
        }
        return snapRepository.save(snap = snap).id!!
    }
}
