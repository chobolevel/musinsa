package com.musinsa.product.updater

import com.musinsa.product.converter.ProductImageConverter
import com.musinsa.product.converter.ProductOptionConverter
import com.musinsa.product.dto.CreateProductImageRequest
import com.musinsa.product.dto.CreateProductOptionRequest
import com.musinsa.product.dto.UpdateProductImageRequest
import com.musinsa.product.dto.UpdateProductOptionRequest
import com.musinsa.product.dto.UpdateProductRequest
import com.musinsa.product.entity.Product
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.entity.ProductCategory
import com.musinsa.product.entity.ProductImage
import com.musinsa.product.entity.ProductOption
import com.musinsa.product.entity.QProductOption.productOption
import com.musinsa.product.repository.ProductBrandRepositoryFacade
import com.musinsa.product.repository.ProductCategoryRepositoryFacade
import com.musinsa.product.vo.ProductUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductUpdater(
    private val productBrandRepository: ProductBrandRepositoryFacade,
    private val productCategoryRepository: ProductCategoryRepositoryFacade,
    private val productOptionUpdater: ProductOptionUpdater,
    private val productImageUpdater: ProductImageUpdater,
    private val productOptionConverter: ProductOptionConverter,
    private val productImageConverter: ProductImageConverter,
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
                    val requestIds: Set<Long> = request.productOptions?.filterIsInstance<UpdateProductOptionRequest>()?.map { it.id!! }?.toSet() ?: emptySet()
                    val deletedProductOptionIds: List<Long> = product.productOptions.filter { it.id!! !in requestIds }.map { it.id!! }
                    product.deleteProductOptionInBatch(productOptionIds = deletedProductOptionIds)

                    val savedProductOptionMap: Map<Long, ProductOption> = product.productOptions.associateBy { it.id!! }

                    request.productOptions?.forEach { request ->
                        when (request) {
                            is CreateProductOptionRequest -> {
                                val productOption: ProductOption = productOptionConverter.toEntity(request = request)
                                product.addProductOption(productOption = productOption)
                            }
                            is UpdateProductOptionRequest -> {
                                val savedProductOption: ProductOption? = savedProductOptionMap[request.id]
                                savedProductOption?.let {
                                    productOptionUpdater.markAsUpdate(
                                        request = request,
                                        productOption = savedProductOption,
                                    )
                                }
                            }
                        }
                    }
                }
                ProductUpdateMask.IMAGE -> {
                    val requestIds: Set<Long> = request.productImages?.filterIsInstance<UpdateProductImageRequest>()?.map { it.id }?.toSet() ?: emptySet()
                    val deletedProductImageIds: List<Long> = product.productOptions.filter { it.id !in requestIds }.map { it.id!! }
                    product.deleteProductImageInBatch(productImageIds = deletedProductImageIds)

                    val savedProductImageMap: Map<Long, ProductImage> = product.productImages.associateBy { it.id!! }

                    request.productImages?.forEach { request ->
                        when (request) {
                            is CreateProductImageRequest -> {
                                val productImage: ProductImage = productImageConverter.toEntity(request = request)
                                product.addProductImage(productImage = productImage)
                            }
                            is UpdateProductImageRequest -> {
                                val savedProductImage: ProductImage? = savedProductImageMap[request.id]
                                savedProductImage?.let {
                                    productImageUpdater.markAsUpdate(
                                        request = request,
                                        productImage = savedProductImage
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        return product
    }
}
