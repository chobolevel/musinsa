package com.musinsa.product.updater

import com.musinsa.product.dto.UpdateProductImageRequest
import com.musinsa.product.entity.ProductImage
import com.musinsa.product.vo.ProductImageUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductImageUpdater {

    fun markAsUpdate(request: UpdateProductImageRequest, productImage: ProductImage): ProductImage {
        request.updateMask.forEach {
            when (it) {
                ProductImageUpdateMask.PATH -> productImage.path = request.path!!
                ProductImageUpdateMask.WIDTH -> productImage.width = request.width!!
                ProductImageUpdateMask.HEIGHT -> productImage.height = request.height!!
                ProductImageUpdateMask.SORT_ORDER -> productImage.sortOrder = request.sortOrder!!
            }
        }
        return productImage
    }
}
