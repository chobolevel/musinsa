package com.musinsa.snap.assembler

import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapComment
import com.musinsa.user.entity.User
import org.springframework.stereotype.Component

@Component
class SnapCommentAssembler {

    fun assemble(
        snapComment: SnapComment,
        parentSnapComment: SnapComment?,
        snap: Snap,
        writer: User
    ): SnapComment {
        snapComment.assignParent(snapComment = parentSnapComment)
        snapComment.assignSnap(snap = snap)
        snapComment.assignWriter(user = writer)
        return snapComment
    }
}
