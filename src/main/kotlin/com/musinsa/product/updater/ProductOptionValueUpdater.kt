package com.musinsa.product.updater

import com.musinsa.product.dto.UpdateProductOptionValueRequest
import com.musinsa.product.entity.ProductOptionValue
import com.musinsa.product.vo.ProductOptionValueUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductOptionValueUpdater {

    fun markAsUpdate(
        request: UpdateProductOptionValueRequest,
        productOptionValue: ProductOptionValue
    ): ProductOptionValue {
        request.updateMask.forEach {
            when (it) {
                ProductOptionValueUpdateMask.NAME -> productOptionValue.name = request.name!!
                ProductOptionValueUpdateMask.SORT_ORDER -> productOptionValue.sortOrder = request.sortOrder!!
            }
        }
        return productOptionValue
    }
}
