package com.musinsa.product.updater

import com.musinsa.product.converter.ProductOptionValueConverter
import com.musinsa.product.dto.CreateProductOptionValueRequest
import com.musinsa.product.dto.UpdateProductOptionRequest
import com.musinsa.product.dto.UpdateProductOptionValueRequest
import com.musinsa.product.entity.ProductOption
import com.musinsa.product.entity.ProductOptionValue
import com.musinsa.product.vo.ProductOptionUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductOptionUpdater(
    private val productOptionValueUpdater: ProductOptionValueUpdater,
    private val productOptionValueConverter: ProductOptionValueConverter,
) {

    fun markAsUpdate(request: UpdateProductOptionRequest, productOption: ProductOption): ProductOption {
        request.updateMask.forEach {
            when (it) {
                ProductOptionUpdateMask.NAME -> productOption.name = request.name!!
                ProductOptionUpdateMask.SORT_ORDER -> productOption.sortOrder = request.sortOrder!!
                ProductOptionUpdateMask.IS_REQUIRED -> productOption.isRequired = request.isRequired!!
                ProductOptionUpdateMask.VALUE -> {
                    val requestIds: Set<Long> = request.values?.filterIsInstance<UpdateProductOptionValueRequest>()?.map { it.id!! }?.toSet() ?: emptySet()
                    val deletedProductOptionValueIds: List<Long> = productOption.productOptionValues.filter { it.id!! !in requestIds }.map { it.id!! }
                    productOption.deleteProductOptionValueInBatch(
                        productOptionValueIds = deletedProductOptionValueIds
                    )

                    val savedProductOptionValueMap: Map<Long, ProductOptionValue> = productOption.productOptionValues.associateBy { it.id!! }

                    request.values?.forEach { request ->
                        when (request) {
                            is CreateProductOptionValueRequest -> {
                                val productOptionValue: ProductOptionValue = productOptionValueConverter.toEntity(request = request)
                                productOption.addProductOptionValue(productOptionValue = productOptionValue)
                            }
                            is UpdateProductOptionValueRequest -> {
                                val savedProductOptionValue: ProductOptionValue? = savedProductOptionValueMap[request.id]
                                savedProductOptionValue?.let {
                                    productOptionValueUpdater.markAsUpdate(
                                        request = request,
                                        productOptionValue = savedProductOptionValue
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        return productOption
    }
}
