package com.musinsa.common.validator

import com.musinsa.common.dto.UploadRequestDto
import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import org.springframework.stereotype.Component

@Component
class UploadParameterValidator {

    private final val availablePrefixList = listOf("image")
    private final val availableExtensionList = listOf("jpg", "jpeg", "png", "gif")

    fun validate(request: UploadRequestDto) {
        if (!availablePrefixList.contains(request.prefix)) {
            throw InvalidParameterException(
                errorCode = ErrorCode.INVALID_PARAMETER,
                message = "${availablePrefixList.joinToString(", ")} 파일(prefix)의 업로드만 지원합니다."
            )
        }
        if (!availableExtensionList.contains(request.extension)) {
            throw InvalidParameterException(
                errorCode = ErrorCode.INVALID_PARAMETER,
                message = "${availableExtensionList.joinToString(", ")} 확장자 파일의 업로드만 지원합니다."
            )
        }
    }
}
