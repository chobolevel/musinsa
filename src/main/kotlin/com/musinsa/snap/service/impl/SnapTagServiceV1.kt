package com.musinsa.snap.service.impl

import com.musinsa.snap.converter.SnapTagConverter
import com.musinsa.snap.dto.CreateSnapTagRequest
import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.entity.SnapTagRepositoryFacade
import com.musinsa.snap.service.SnapTagService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SnapTagServiceV1(
    private val repository: SnapTagRepositoryFacade,
    private val converter: SnapTagConverter
) : SnapTagService {

    @Transactional
    override fun createSnapTag(request: CreateSnapTagRequest): Long {
        val snapTag: SnapTag = converter.toEntity(request = request)
        return repository.save(snapTag = snapTag).id!!
    }
}
