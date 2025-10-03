package com.musinsa.snap

import com.musinsa.snap.entity.Snap

object DummySnap {
    private val id: Long = 1L
    private val content: String = "오늘 날씨는 맑음!"
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummySnap: Snap by lazy {
        Snap(
            content = content
        ).also {
            it.id = id
        }
    }

    fun toEntity(): Snap {
        return dummySnap
    }
}
