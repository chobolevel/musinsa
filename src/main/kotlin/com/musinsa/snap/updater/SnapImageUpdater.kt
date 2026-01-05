package com.musinsa.snap.updater

import com.musinsa.snap.dto.UpdateSnapImageRequest
import com.musinsa.snap.entity.SnapImage
import com.musinsa.snap.vo.SnapImageUpdateMask
import org.springframework.stereotype.Component

@Component
class SnapImageUpdater {

    fun markAsUpdate(
        request: UpdateSnapImageRequest,
        snapImage: SnapImage
    ): SnapImage {
        request.updateMask.forEach {
            when (it) {
                SnapImageUpdateMask.PATH -> snapImage.path = request.path!!
                SnapImageUpdateMask.WIDTH -> snapImage.width = request.width!!
                SnapImageUpdateMask.HEIGHT -> snapImage.height = request.height!!
                SnapImageUpdateMask.SORT_ORDER -> snapImage.sortOrder = request.sortOrder!!
            }
        }
        return snapImage
    }
}
