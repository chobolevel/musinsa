package com.musinsa.product.brand

import com.musinsa.common.vo.NationType
import com.musinsa.product.dto.CreateProductBrandRequest
import com.musinsa.product.dto.ProductBrandResponse
import com.musinsa.product.dto.UpdateProductBrandRequest
import com.musinsa.product.entity.ProductBrand
import com.musinsa.product.vo.ProductBrandUpdateMask

object DummyProductBrand {
    private const val id: Long = 1L
    private const val name: String = "오프닝 프로젝트"
    private const val englishName: String = "OPENING PROJECT"
    private const val yearOfLaunch: Int = 2022
    private val nation: NationType = NationType.KOREA
    private const val description: String = "오프닝프로젝트(OPENING PROJECT)는 현대적 감각을 담아, 일상 속에서 편하게 입을 수 있으며 자유분방하면서도 영한 개성을 드러낼 수 있는 스타일을 제안하는 브랜드입니다. 편안함과 자유로움을 바탕으로, 트렌디한 색감과 그래픽, 디테일을 더해 새로운 감각을 전달합니다."
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummyProductBrand: ProductBrand by lazy {
        ProductBrand(
            name = name,
            englishName = englishName,
            yearOfLaunch = yearOfLaunch,
            nation = nation,
            description = description,
        ).also { it.id = id }
    }

    private val dummyBrandResponse: ProductBrandResponse by lazy {
        ProductBrandResponse(
            id = id,
            name = name,
            englishName = englishName,
            yearOfLaunch = yearOfLaunch,
            nation = nation,
            nationKoreanName = nation.koreanName,
            description = description,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    private val dummyCreateRequest: CreateProductBrandRequest by lazy {
        CreateProductBrandRequest(
            name = name,
            englishName = englishName,
            yearOfLaunch = yearOfLaunch,
            nation = nation,
            description = description,
        )
    }

    private val dummyUpdateRequest: UpdateProductBrandRequest by lazy {
        UpdateProductBrandRequest(
            name = "뉴 오프닝 프로젝트",
            englishName = null,
            yearOfLaunch = null,
            nation = null,
            description = null,
            updateMask = listOfNotNull(ProductBrandUpdateMask.NAME)
        )
    }

    fun toEntity(): ProductBrand = dummyProductBrand

    fun toResponse(): ProductBrandResponse = dummyBrandResponse

    fun toCreateRequest(): CreateProductBrandRequest = dummyCreateRequest

    fun toUpdateRequest(): UpdateProductBrandRequest = dummyUpdateRequest
}
