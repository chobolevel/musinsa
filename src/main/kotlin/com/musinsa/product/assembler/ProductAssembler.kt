package com.musinsa.product.assembler

import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.entity.ProductOption
import org.springframework.stereotype.Component

@Component
class ProductAssembler {

    fun assemble(
        product: Product,
        productBrand: ProductBrand,
        productCategory: ProductCategory,
        productOptions: List<ProductOption>
    ): Product {
        product.assignProductBrand(productBrand = productBrand)
        product.assignProductCategory(productCategory = productCategory)
        productOptions.forEach { productOption ->
            productOption.flushProductOptionValues()
            product.addProductOption(productOption = productOption)
        }

        return product
    }
}
