package com.musinsa.product.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.product.dto.UpdateProductImageRequest
import com.musinsa.product.vo.ProductImageUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductImageParameterValidator {

    fun validate(request: UpdateProductImageRequest) {
        request.updateMask.forEach {
            when (it) {
                ProductImageUpdateMask.PATH -> {
                    if (request.path.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductImageUpdateMask.PATH.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }
                ProductImageUpdateMask.WIDTH -> {
                    if (request.width == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductImageUpdateMask.WIDTH.fieldName}]"
                        )
                    }
                }

                ProductImageUpdateMask.HEIGHT -> {
                    if (request.height == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductImageUpdateMask.HEIGHT.fieldName}]"
                        )
                    }
                }

                ProductImageUpdateMask.SORT_ORDER -> {
                    if (request.sortOrder == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductImageUpdateMask.SORT_ORDER.fieldName}]"
                        )
                    }
                }
            }
        }
    }
}
