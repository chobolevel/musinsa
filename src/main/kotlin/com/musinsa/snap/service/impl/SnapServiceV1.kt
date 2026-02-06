package com.musinsa.snap.service.impl

import com.musinsa.common.dto.Pagination
import com.musinsa.common.dto.PaginationResponse
import com.musinsa.snap.assembler.SnapAssembler
import com.musinsa.snap.converter.SnapConverter
import com.musinsa.snap.converter.SnapImageConverter
import com.musinsa.snap.dto.CreateSnapRequest
import com.musinsa.snap.dto.SnapResponse
import com.musinsa.snap.dto.UpdateSnapRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapImage
import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.reader.SnapQueryFilter
import com.musinsa.snap.reader.SnapReader
import com.musinsa.snap.reader.SnapTagReader
import com.musinsa.snap.service.SnapService
import com.musinsa.snap.store.SnapStore
import com.musinsa.snap.updater.SnapUpdater
import com.musinsa.snap.validator.SnapBusinessValidator
import com.musinsa.snap.vo.SnapOrderType
import com.musinsa.user.entity.User
import com.musinsa.user.reader.UserReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SnapServiceV1(
    private val converter: SnapConverter,
    private val snapImageConverter: SnapImageConverter,
    private val userReader: UserReader,
    private val snapReader: SnapReader,
    private val snapTagReader: SnapTagReader,
    private val snapStore: SnapStore,
    private val assembler: SnapAssembler,
    private val validator: SnapBusinessValidator,
    private val updater: SnapUpdater
) : SnapService {

    @Transactional
    override fun createSnap(userId: Long, request: CreateSnapRequest): Long {
        val baseSnap: Snap = converter.toEntity(request = request)
        val user: User = userReader.findById(id = userId)
        val snapTags: List<SnapTag> = snapTagReader.findByIds(ids = request.snapTagIds)
        val snapImages: List<SnapImage> = snapImageConverter.toEntityInBatch(requests = request.snapImages)

        val snap: Snap = assembler.assemble(
            snap = baseSnap,
            writer = user,
            snapTags = snapTags,
            snapImages = snapImages,
        )

        return snapStore.save(snap = snap).id!!
    }

    @Transactional(readOnly = true)
    override fun getSnaps(
        queryFilter: SnapQueryFilter,
        pagination: Pagination,
        orderTypes: List<SnapOrderType>
    ): PaginationResponse {
        val snaps: List<Snap> = snapReader.searchSnaps(
            queryFilter = queryFilter,
            pagination = pagination,
            orderTypes = orderTypes
        )
        val totalCount: Long = snapReader.searchSnapsCount(queryFilter = queryFilter)
        return PaginationResponse(
            page = pagination.page,
            size = pagination.size,
            data = converter.toResponseInBatch(snaps = snaps),
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    override fun getSnap(id: Long): SnapResponse {
        val snap: Snap = snapReader.findById(id = id)
        return converter.toResponse(snap = snap)
    }

    @Transactional
    override fun updateSnap(userId: Long, snapId: Long, request: UpdateSnapRequest): Long {
        val snap: Snap = snapReader.findById(id = snapId)
        validator.validateWriter(
            userId = userId,
            snap = snap
        )
        updater.markAsUpdate(
            request = request,
            snap = snap
        )
        return snapId
    }

    @Transactional
    override fun deleteSnap(userId: Long, snapId: Long): Boolean {
        val snap: Snap = snapReader.findById(id = snapId)
        validator.validateWriter(
            userId = userId,
            snap = snap
        )
        snap.delete()
        return snap.isDeleted
    }
}
