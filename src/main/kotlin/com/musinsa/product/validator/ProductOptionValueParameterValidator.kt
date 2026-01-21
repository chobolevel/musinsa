package com.musinsa.product.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.product.dto.UpdateProductOptionValueRequest
import com.musinsa.product.vo.ProductOptionValueUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductOptionValueParameterValidator {

    fun validate(request: UpdateProductOptionValueRequest) {
        request.updateMask.forEach {
            when (it) {
                ProductOptionValueUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductOptionValueUpdateMask.NAME.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductOptionValueUpdateMask.SORT_ORDER -> {
                    if (request.sortOrder == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductOptionValueUpdateMask.SORT_ORDER.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }
            }
        }
    }
}
