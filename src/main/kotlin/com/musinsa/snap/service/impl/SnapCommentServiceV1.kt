package com.musinsa.snap.service.impl

import com.musinsa.snap.converter.SnapCommentConverter
import com.musinsa.snap.dto.CreateSnapCommentRequest
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapComment
import com.musinsa.snap.repository.SnapCommentRepositoryFacade
import com.musinsa.snap.repository.SnapRepositoryFacade
import com.musinsa.snap.service.SnapCommentService
import com.musinsa.user.entity.User
import com.musinsa.user.entity.UserRepositoryFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SnapCommentServiceV1(
    private val repository: SnapCommentRepositoryFacade,
    private val userRepository: UserRepositoryFacade,
    private val snapRepository: SnapRepositoryFacade,
    private val converter: SnapCommentConverter
) : SnapCommentService {

    @Transactional
    override fun createSnapComment(
        userId: Long,
        snapId: Long,
        request: CreateSnapCommentRequest
    ): Long {
        val user: User = userRepository.findById(id = userId)
        val snap: Snap = snapRepository.findById(id = snapId)
        val snapComment: SnapComment = converter.toEntity(request = request).also { snapComment ->
            snapComment.assignSnap(snap = snap)
            snapComment.assignUser(user = user)
        }
        return repository.save(snapComment = snapComment).id!!
    }
}
