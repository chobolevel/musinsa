package com.musinsa.product.updater

import com.musinsa.product.dto.UpdateProductCategoryRequest
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.repository.ProductCategoryRepositoryFacade
import com.musinsa.product.vo.ProductCategoryUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductCategoryUpdater(
    private val productCategoryRepository: ProductCategoryRepositoryFacade
) {

    fun markAsUpdate(
        productCategory: ProductCategory,
        request: UpdateProductCategoryRequest
    ): ProductCategory {
        request.updateMask.forEach {
            when (it) {
                ProductCategoryUpdateMask.PARENT -> {
                    val parent: ProductCategory? = request.parentId?.let { productCategoryRepository.findById(id = request.parentId) }
                    productCategory.assignParent(parent = parent)
                }
                ProductCategoryUpdateMask.NAME -> {
                    productCategory.name = request.name!!
                }
                ProductCategoryUpdateMask.ICON_IMAGE_URL -> {
                    productCategory.iconImageUrl = request.iconImageUrl!!
                }
            }
        }
        return productCategory
    }
}
