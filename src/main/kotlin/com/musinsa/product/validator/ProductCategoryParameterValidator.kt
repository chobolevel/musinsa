package com.musinsa.product.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.product.dto.UpdateProductCategoryRequest
import com.musinsa.product.vo.ProductCategoryUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductCategoryParameterValidator {

    fun validate(request: UpdateProductCategoryRequest) {
        request.updateMask.forEach {
            when (it) {
                ProductCategoryUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductCategoryUpdateMask.NAME.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductCategoryUpdateMask.ICON_IMAGE_PATH -> {
                    if (request.iconImagePath.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductCategoryUpdateMask.ICON_IMAGE_PATH.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                else -> Unit
            }
        }
    }
}
