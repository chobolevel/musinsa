package com.musinsa.snap.updater

import com.musinsa.snap.dto.UpdateSnapRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.vo.SnapUpdateMask
import org.springframework.stereotype.Component

@Component
class SnapUpdater {

    fun markAsUpdate(request: UpdateSnapRequest, snap: Snap): Snap {
        request.updateMasks.forEach {
            when (it) {
                SnapUpdateMask.CONTENT -> snap.content = request.content
            }
        }
        return snap
    }
}
