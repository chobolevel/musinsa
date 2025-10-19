package com.musinsa.snap.updater

import com.musinsa.snap.dto.UpdateSnapCommentRequest
import com.musinsa.snap.entity.SnapComment
import com.musinsa.snap.vo.SnapCommentUpdateMask
import org.springframework.stereotype.Component

@Component
class SnapCommentUpdater {

    fun markAsUpdate(request: UpdateSnapCommentRequest, snapComment: SnapComment): SnapComment {
        request.updateMasks.forEach {
            when (it) {
                SnapCommentUpdateMask.COMMENT -> snapComment.comment = request.comment!!
            }
        }
        return snapComment
    }
}
