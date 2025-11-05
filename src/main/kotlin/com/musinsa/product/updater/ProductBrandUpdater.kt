package com.musinsa.product.updater

import com.musinsa.product.dto.UpdateProductBrandRequest
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.vo.ProductBrandUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductBrandUpdater {

    fun markAsUpdate(request: UpdateProductBrandRequest, productBrand: ProductBrand): ProductBrand {
        request.updateMask.forEach {
            when (it) {
                ProductBrandUpdateMask.NAME -> productBrand.name = request.name!!
                ProductBrandUpdateMask.ENGLISH_NAME -> productBrand.englishName = request.englishName
                ProductBrandUpdateMask.YEAR_OF_LAUNCH -> productBrand.yearOfLaunch = request.yearOfLaunch!!
                ProductBrandUpdateMask.NATION -> productBrand.nation = request.nation!!
                ProductBrandUpdateMask.DESCRIPTION -> productBrand.description = request.description!!
            }
        }
        return productBrand
    }
}
