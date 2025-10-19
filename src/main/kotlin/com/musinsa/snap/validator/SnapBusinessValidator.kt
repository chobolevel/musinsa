package com.musinsa.snap.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.PolicyViolationException
import com.musinsa.snap.entity.Snap
import org.springframework.stereotype.Component

@Component
class SnapBusinessValidator {

    @Throws(PolicyViolationException::class)
    fun validateWriter(userId: Long, snap: Snap) {
        if (userId != snap.writer!!.id) {
            throw PolicyViolationException(
                errorCode = ErrorCode.ACCESSIBLE_ONLY_WRITER_ON_SNAP,
                message = ErrorCode.ACCESSIBLE_ONLY_WRITER_ON_SNAP.defaultMessage
            )
        }
    }
}
