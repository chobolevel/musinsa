package com.musinsa.snap.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.snap.dto.UpdateSnapRequest
import com.musinsa.snap.vo.SnapUpdateMask
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class SnapParameterValidator {

    @Throws(InvalidParameterException::class)
    fun validate(request: UpdateSnapRequest) {
        request.updateMasks.forEach {
            when (it) {
                SnapUpdateMask.CONTENT -> {
                    if (request.content.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${SnapUpdateMask.CONTENT.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }
                SnapUpdateMask.SNAP_IMAGE -> {
                    if (request.snapImages.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${SnapUpdateMask.SNAP_IMAGE.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }
                else -> Unit
            }
        }
    }
}
