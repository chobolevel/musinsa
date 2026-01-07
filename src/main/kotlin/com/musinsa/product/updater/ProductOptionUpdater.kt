package com.musinsa.product.updater

import com.musinsa.product.dto.UpdateProductOptionRequest
import com.musinsa.product.entity.ProductOption
import com.musinsa.product.vo.ProductOptionUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductOptionUpdater {

    fun markAsUpdate(request: UpdateProductOptionRequest, productOption: ProductOption): ProductOption {
        request.updateMask.forEach {
            when (it) {
                ProductOptionUpdateMask.NAME -> productOption.name = request.name!!
                ProductOptionUpdateMask.SORT_ORDER -> productOption.sortOrder = request.sortOrder!!
                ProductOptionUpdateMask.IS_REQUIRED -> productOption.isRequired = request.isRequired!!
                else -> Unit
            }
        }
        return productOption
    }
}
