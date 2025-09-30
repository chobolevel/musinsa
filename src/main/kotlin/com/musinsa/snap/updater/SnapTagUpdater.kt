package com.musinsa.snap.updater

import com.musinsa.snap.dto.UpdateSnapTagRequest
import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.vo.SnapTagUpdateMask
import org.springframework.stereotype.Component

@Component
class SnapTagUpdater {

    fun markAsUpdate(request: UpdateSnapTagRequest, entity: SnapTag): SnapTag {
        request.updateMask.forEach {
            when (it) {
                SnapTagUpdateMask.TYPE -> entity.type = request.type!!
                SnapTagUpdateMask.NAME -> entity.name = request.name!!
            }
        }
        return entity
    }
}
