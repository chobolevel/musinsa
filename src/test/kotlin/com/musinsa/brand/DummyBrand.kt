package com.musinsa.brand

import com.musinsa.brand.entity.Brand
import com.musinsa.common.vo.NationType

object DummyBrand {
    private const val id: Long = 1L
    private const val name: String = "오프닝 프로젝트"
    private const val englishName: String = "OPENING PROJECT"
    private const val yearOfLaunch: Int = 2022
    private val nation: NationType = NationType.KOREA
    private const val description: String = "오프닝프로젝트(OPENING PROJECT)는 현대적 감각을 담아, 일상 속에서 편하게 입을 수 있으며 자유분방하면서도 영한 개성을 드러낼 수 있는 스타일을 제안하는 브랜드입니다. 편안함과 자유로움을 바탕으로, 트렌디한 색감과 그래픽, 디테일을 더해 새로운 감각을 전달합니다."

    private val dummyBrand: Brand by lazy {
        Brand(
            name = name,
            englishName = englishName,
            yearOfLaunch = yearOfLaunch,
            nation = nation,
            description = description,
        ).also { it.id = id }
    }

    fun toEntity(): Brand = dummyBrand
}
