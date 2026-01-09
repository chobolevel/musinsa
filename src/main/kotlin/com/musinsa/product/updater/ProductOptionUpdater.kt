package com.musinsa.product.updater

import com.musinsa.product.dto.UpdateProductOptionRequest
import com.musinsa.product.entity.ProductOption
import com.musinsa.product.entity.ProductOptionValue
import com.musinsa.product.vo.ProductOptionUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductOptionUpdater(
    private val productOptionValueUpdater: ProductOptionValueUpdater
) {

    fun markAsUpdate(request: UpdateProductOptionRequest, productOption: ProductOption): ProductOption {
        request.updateMask.forEach {
            when (it) {
                ProductOptionUpdateMask.NAME -> productOption.name = request.name!!
                ProductOptionUpdateMask.SORT_ORDER -> productOption.sortOrder = request.sortOrder!!
                ProductOptionUpdateMask.IS_REQUIRED -> productOption.isRequired = request.isRequired!!
                ProductOptionUpdateMask.VALUE -> {
                    val savedProductOptionValueMap: Map<Long, ProductOptionValue> = productOption.productOptionValues.associateBy { it.id!! }

                    request.values?.forEach { updateProductOptionValueRequest ->
                        val savedProductOptionValue: ProductOptionValue? = savedProductOptionValueMap[updateProductOptionValueRequest.id]
                        if (savedProductOptionValue != null) {
                            productOptionValueUpdater.markAsUpdate(
                                request = updateProductOptionValueRequest,
                                productOptionValue = savedProductOptionValue
                            )
                        } else {
                            // 신규 상품 옵션 값 등록
                        }
                    }

                    val requestIds: List<Long> = request.values?.map { it.id!! } ?: emptyList()
                    val deletedProductOptionValueIds: List<Long> = productOption.productOptionValues.filter { it.id !in requestIds }.map { it.id!! }
                    productOption.deleteProductOptionValueInBatch(
                        productOptionValueIds = deletedProductOptionValueIds
                    )
                }
            }
        }
        return productOption
    }
}
