package com.musinsa.snap.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.snap.dto.UpdateSnapImageRequest
import com.musinsa.snap.vo.SnapImageUpdateMask
import org.springframework.stereotype.Component

@Component
class SnapImageParameterValidator {

    fun validate(request: UpdateSnapImageRequest) {
        request.updateMask.forEach {
            when (it) {
                SnapImageUpdateMask.PATH -> {
                    if (request.path.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${SnapImageUpdateMask.PATH.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                SnapImageUpdateMask.WIDTH -> {
                    if (request.width == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${SnapImageUpdateMask.WIDTH.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                SnapImageUpdateMask.HEIGHT -> {
                    if (request.height == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${SnapImageUpdateMask.HEIGHT.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                SnapImageUpdateMask.SORT_ORDER -> {
                    if (request.sortOrder == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${SnapImageUpdateMask.SORT_ORDER.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }
            }
        }
    }
}
