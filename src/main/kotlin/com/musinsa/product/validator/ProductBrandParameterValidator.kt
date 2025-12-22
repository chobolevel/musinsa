package com.musinsa.product.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.product.dto.UpdateProductBrandRequest
import com.musinsa.product.vo.ProductBrandUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductBrandParameterValidator {

    fun validate(request: UpdateProductBrandRequest) {
        request.updateMask.forEach {
            when (it) {
                ProductBrandUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductBrandUpdateMask.NAME.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }
                ProductBrandUpdateMask.YEAR_OF_LAUNCH -> {
                    if (request.yearOfLaunch == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductBrandUpdateMask.YEAR_OF_LAUNCH.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductBrandUpdateMask.NATION -> {
                    if (request.nation == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductBrandUpdateMask.NATION.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductBrandUpdateMask.DESCRIPTION -> {
                    if (request.description.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductBrandUpdateMask.DESCRIPTION.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                else -> Unit
            }
        }
    }
}
