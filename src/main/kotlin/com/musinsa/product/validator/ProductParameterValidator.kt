package com.musinsa.product.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.product.dto.UpdateProductRequest
import com.musinsa.product.vo.ProductUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductParameterValidator {

    fun validate(request: UpdateProductRequest) {
        request.updateMask.forEach {
            when (it) {
                ProductUpdateMask.PRODUCT_BRAND -> {
                    if (request.productBrandId == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[product_brand_id]으(는) 필수 값입니다."
                        )
                    }
                }

                ProductUpdateMask.PRODUCT_CATEGORY -> {
                    if (request.productCategoryId == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[product_category_id]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[name]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductUpdateMask.STANDARD_PRICE -> {
                    if (request.standardPrice == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[standard_price]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductUpdateMask.SALE_STATUS -> {
                    if (request.saleStatus == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[sale_status]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductUpdateMask.SORT_ORDER -> {
                    if (request.sortOrder == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[sort_order]은(는) 필수 값입니다."
                        )
                    }
                }

                else -> Unit
            }
        }
    }
}
