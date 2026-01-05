package com.musinsa.snap.updater

import com.musinsa.snap.converter.SnapImageConverter
import com.musinsa.snap.dto.CreateSnapImageRequest
import com.musinsa.snap.dto.UpdateSnapRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapImage
import com.musinsa.snap.vo.SnapUpdateMask
import org.springframework.stereotype.Component

@Component
class SnapUpdater(
    private val snapImageUpdater: SnapImageUpdater,
    private val snapImageConverter: SnapImageConverter,
) {

    fun markAsUpdate(request: UpdateSnapRequest, snap: Snap): Snap {
        request.updateMasks.forEach {
            when (it) {
                SnapUpdateMask.CONTENT -> snap.content = request.content
                SnapUpdateMask.SNAP_IMAGE -> {
                    val savedSnapImageMap: Map<Long, SnapImage> = snap.snapImages.associateBy { it.id!! }

                    request.snapImages?.forEach { updateSnapImageRequest ->
                        val savedSnapImage: SnapImage? = savedSnapImageMap[updateSnapImageRequest.id]

                        if (savedSnapImage != null) {
                            // 단건 수정
                            snapImageUpdater.markAsUpdate(
                                request = updateSnapImageRequest,
                                snapImage = savedSnapImage
                            )
                        } else {
                            // 신규 등록
                            val createRequest = CreateSnapImageRequest(
                                path = updateSnapImageRequest.path!!,
                                width = updateSnapImageRequest.width ?: 0,
                                height = updateSnapImageRequest.height ?: 0,
                                sortOrder = updateSnapImageRequest.sortOrder ?: 0
                            )
                            val snapImage: SnapImage = snapImageConverter.toEntity(request = createRequest)
                            snap.addSnapImage(snapImage = snapImage)
                        }
                    }

                    // 저장되어 있지만 목록에 없는 엔티티 삭제
                    val requestIds: List<Long> = request.snapImages?.mapNotNull { it.id } ?: emptyList()
                    val deletedSnapImageIds: List<Long> = snap.snapImages.filter { it.id !in requestIds }.map { it.id!! }
                    snap.deleteSnapImageInBatch(
                        snapImageIds = deletedSnapImageIds
                    )
                }
            }
        }
        return snap
    }
}
