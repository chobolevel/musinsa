package com.musinsa.product.assembler

import com.musinsa.product.entity.ProductCategory
import org.springframework.stereotype.Component

@Component
class ProductCategoryAssembler {

    fun assemble(
        productCategory: ProductCategory,
        parent: ProductCategory?
    ): ProductCategory {
        parent?.let { productCategory.assignParent(parent = it) }
        return productCategory
    }
}
