package com.musinsa.product.updater

import com.musinsa.product.converter.ProductOptionConverter
import com.musinsa.product.dto.UpdateProductRequest
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.entity.ProductOption
import com.musinsa.product.repository.ProductBrandRepositoryFacade
import com.musinsa.product.repository.ProductCategoryRepositoryFacade
import com.musinsa.product.vo.ProductUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductUpdater(
    private val productBrandRepository: ProductBrandRepositoryFacade,
    private val productCategoryRepository: ProductCategoryRepositoryFacade,
    private val productOptionUpdater: ProductOptionUpdater,
    private val productOptionConverter: ProductOptionConverter,
) {

    fun markAsUpdate(request: UpdateProductRequest, product: Product): Product {
        request.updateMask.forEach {
            when (it) {
                ProductUpdateMask.PRODUCT_BRAND -> {
                    val productBrand: ProductBrand = productBrandRepository.findById(id = request.productBrandId!!)
                    product.assignProductBrand(productBrand = productBrand)
                }
                ProductUpdateMask.PRODUCT_CATEGORY -> {
                    val productCategory: ProductCategory = productCategoryRepository.findById(id = request.productCategoryId!!)
                    product.assignProductCategory(productCategory = productCategory)
                }
                ProductUpdateMask.NAME -> product.name = request.name!!
                ProductUpdateMask.DESCRIPTION -> product.description = request.description
                ProductUpdateMask.STANDARD_PRICE -> product.standardPrice = request.standardPrice!!
                ProductUpdateMask.SALE_STATUS -> product.saleStatus = request.saleStatus!!
                ProductUpdateMask.SORT_ORDER -> product.sortOrder = request.sortOrder!!
                ProductUpdateMask.OPTION -> {
                    val savedProductOptionMap: Map<Long, ProductOption> = product.productOptions.associateBy { it.id!! }

                    request.productOptions?.forEach { updateProductOptionRequest ->
                        val savedProductOption: ProductOption? = savedProductOptionMap[updateProductOptionRequest.id]

                        if (savedProductOption != null) {
                            productOptionUpdater.markAsUpdate(
                                request = updateProductOptionRequest,
                                productOption = savedProductOption
                            )
                        } else {
                            // 신규 상품 옵션 등록 로직 고민해보기
                        }
                    }

                    val requestIds: List<Long> = request.productOptions?.map { it.id!! } ?: emptyList()
                    val deletedProductOptionIds: List<Long> = product.productOptions.filter { it.id!! !in requestIds }.map { it.id!! }
                    product.deleteProductOptionInBatch(productOptionIds = deletedProductOptionIds)
                }
            }
        }
        return product
    }
}
