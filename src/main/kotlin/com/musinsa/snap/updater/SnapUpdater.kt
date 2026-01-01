package com.musinsa.snap.updater

import com.musinsa.snap.converter.SnapImageConverter
import com.musinsa.snap.dto.UpdateSnapRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapImage
import com.musinsa.snap.vo.SnapUpdateMask
import org.springframework.stereotype.Component

@Component
class SnapUpdater(
    private val snapImageConverter: SnapImageConverter
) {

    fun markAsUpdate(request: UpdateSnapRequest, snap: Snap): Snap {
        request.updateMasks.forEach {
            when (it) {
                SnapUpdateMask.CONTENT -> snap.content = request.content
                SnapUpdateMask.SNAP_IMAGE -> {
                    snap.deleteSnapImageInBatch()
                    val snapImages: List<SnapImage> = snapImageConverter.toEntityInBatch(requests = request.snapImages!!)
                    snapImages.forEach { snap.addSnapImage(snapImage = it) }
                }
            }
        }
        return snap
    }
}
