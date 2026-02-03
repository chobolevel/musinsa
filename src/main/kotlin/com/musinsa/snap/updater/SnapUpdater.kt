package com.musinsa.snap.updater

import com.musinsa.snap.converter.SnapImageConverter
import com.musinsa.snap.dto.CreateSnapImageRequest
import com.musinsa.snap.dto.UpdateSnapImageRequest
import com.musinsa.snap.dto.UpdateSnapRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapImage
import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.reader.SnapTagReader
import com.musinsa.snap.vo.SnapUpdateMask
import org.springframework.stereotype.Component

@Component
class SnapUpdater(
    private val snapImageUpdater: SnapImageUpdater,
    private val snapImageConverter: SnapImageConverter,
    private val snapTagReader: SnapTagReader
) {

    fun markAsUpdate(request: UpdateSnapRequest, snap: Snap): Snap {
        request.updateMasks.forEach {
            when (it) {
                SnapUpdateMask.CONTENT -> snap.content = request.content
                SnapUpdateMask.SNAP_TAG -> {
                    val savedSnapTagMap: Map<Long, SnapTag> = snap.snapTagMappings.map { it.snapTag!! }.associateBy { it.id!! }

                    request.snapTagIds?.forEach { snapTagId ->
                        val savedSnapTag: SnapTag? = savedSnapTagMap[snapTagId]
                        if (savedSnapTag == null) {
                            val snapTag: SnapTag = snapTagReader.findById(id = snapTagId)
                            snap.addSnapTag(snapTag = snapTag)
                        }
                    }

                    val requestIds: Set<Long> = request.snapTagIds?.toSet() ?: emptySet()
                    val deletedSnapTagIds: List<Long> = snap.snapTagMappings.map { it.snapTag!!.id!! }.filter { it !in requestIds }
                    snap.deleteSnapTagInBatch(snapTagIds = deletedSnapTagIds)
                }
                SnapUpdateMask.SNAP_IMAGE -> {
                    val savedSnapImageMap: Map<Long, SnapImage> = snap.snapImages.associateBy { it.id!! }

                    request.snapImages?.forEach { request ->
                        when (request) {
                            is CreateSnapImageRequest -> {
                                val snapImage: SnapImage = snapImageConverter.toEntity(request = request)
                                snap.addSnapImage(snapImage = snapImage)
                            }
                            is UpdateSnapImageRequest -> {
                                val savedSnapImage: SnapImage? = savedSnapImageMap[request.id]
                                savedSnapImage?.let {
                                    snapImageUpdater.markAsUpdate(
                                        request = request,
                                        snapImage = savedSnapImage
                                    )
                                }
                            }
                        }
                    }

                    val requestIds: Set<Long> = request.snapImages?.filterIsInstance<UpdateSnapImageRequest>()?.map { it.id!! }?.toSet() ?: emptySet()
                    val deletedSnapImageIds: List<Long> = snap.snapImages.filter { it.id!! !in requestIds }.map { it.id!! }
                    snap.deleteSnapImageInBatch(
                        snapImageIds = deletedSnapImageIds,
                    )
                }
            }
        }
        return snap
    }
}
