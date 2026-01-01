package com.musinsa.snap.assembler

import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapImage
import com.musinsa.snap.entity.SnapTag
import com.musinsa.user.entity.User
import org.springframework.stereotype.Component

@Component
class SnapAssembler {

    fun assemble(
        snap: Snap,
        writer: User,
        snapTags: List<SnapTag>,
        snapImages: List<SnapImage>
    ): Snap {
        snap.assignWriter(user = writer)
        snapTags.forEach { snap.addSnapTag(snapTag = it) }
        snapImages.forEach { snap.addSnapImage(snapImage = it) }
        return snap
    }
}
