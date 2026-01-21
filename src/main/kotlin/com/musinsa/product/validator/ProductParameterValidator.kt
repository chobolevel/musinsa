package com.musinsa.product.validator

import com.musinsa.common.exception.ErrorCode
import com.musinsa.common.exception.InvalidParameterException
import com.musinsa.product.dto.UpdateProductImageRequest
import com.musinsa.product.dto.UpdateProductOptionRequest
import com.musinsa.product.dto.UpdateProductRequest
import com.musinsa.product.vo.ProductUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductParameterValidator(
    private val productOptionParameterValidator: ProductOptionParameterValidator,
    private val productImageParameterValidator: ProductImageParameterValidator,
) {

    fun validate(request: UpdateProductRequest) {
        request.updateMask.forEach {
            when (it) {
                ProductUpdateMask.PRODUCT_BRAND -> {
                    if (request.productBrandId == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductUpdateMask.PRODUCT_BRAND.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductUpdateMask.PRODUCT_CATEGORY -> {
                    if (request.productCategoryId == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductUpdateMask.PRODUCT_CATEGORY.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductUpdateMask.NAME.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductUpdateMask.STANDARD_PRICE -> {
                    if (request.standardPrice == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductUpdateMask.STANDARD_PRICE.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductUpdateMask.SALE_STATUS -> {
                    if (request.saleStatus == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductUpdateMask.SALE_STATUS.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductUpdateMask.SORT_ORDER -> {
                    if (request.sortOrder == null) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductUpdateMask.SORT_ORDER.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                }

                ProductUpdateMask.OPTION -> {
                    if (request.productOptions?.filterIsInstance<UpdateProductOptionRequest>().isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductUpdateMask.OPTION.fieldName}]은(는) 필수 값입니다."
                        )
                    }
                    request.productOptions
                        .filterIsInstance<UpdateProductOptionRequest>()
                        .forEach { updateProductOptionRequest ->
                            productOptionParameterValidator.validate(request = updateProductOptionRequest)
                        }
                }

                ProductUpdateMask.IMAGE -> {
                    if (request.productImages?.filterIsInstance<UpdateProductImageRequest>().isNullOrEmpty()) {
                        throw InvalidParameterException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "[${ProductUpdateMask.IMAGE.fieldName}]"
                        )
                    }
                    request.productImages
                        .filterIsInstance<UpdateProductImageRequest>()
                        .forEach { updateProductImageRequest ->
                            productImageParameterValidator.validate(request = updateProductImageRequest)
                        }
                }

                else -> Unit
            }
        }
    }
}
