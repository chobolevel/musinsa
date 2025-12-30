package com.musinsa.snap.assembler

import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapLike
import com.musinsa.user.entity.User
import org.springframework.stereotype.Component

@Component
class SnapLikeAssembler {

    fun assemble(
        snapLike: SnapLike,
        snap: Snap,
        user: User
    ): SnapLike {
        snapLike.assignSnap(snap = snap)
        snapLike.assignUser(user = user)
        return snapLike
    }
}
