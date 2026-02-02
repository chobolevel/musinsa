package com.musinsa.snap.store

import com.musinsa.snap.entity.SnapComment
import com.musinsa.snap.repository.SnapCommentRepository
import org.springframework.stereotype.Component

@Component
class SnapCommentStore(
    private val snapCommentRepository: SnapCommentRepository
) {

    fun save(snapComment: SnapComment): SnapComment {
        return snapCommentRepository.save(snapComment)
    }
}
