package com.musinsa.product.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.product.dto.UpdateProductOptionRequest
import com.musinsa.product.dto.UpdateProductOptionValueRequest
import com.musinsa.product.vo.ProductOptionUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductOptionParameterValidator(
    private val productOptionValueParameterValidator: ProductOptionValueParameterValidator,
) {

    fun validate(request: UpdateProductOptionRequest) {
        request.updateMask.forEach {
            when (it) {
                ProductOptionUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductOptionUpdateMask.NAME.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductOptionUpdateMask.SORT_ORDER -> {
                    if (request.sortOrder == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductOptionUpdateMask.SORT_ORDER.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductOptionUpdateMask.IS_REQUIRED -> {
                    if (request.isRequired == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductOptionUpdateMask.IS_REQUIRED.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductOptionUpdateMask.VALUE -> {
                    if (request.values?.filterIsInstance<UpdateProductOptionValueRequest>().isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductOptionUpdateMask.VALUE.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                    request.values
                        .filterIsInstance<UpdateProductOptionValueRequest>()
                        .forEach { updateProductOptionValueRequest ->
                            productOptionValueParameterValidator.validate(request = updateProductOptionValueRequest)
                        }
                }
            }
        }
    }
}
