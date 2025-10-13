package com.musinsa.snap

import com.musinsa.snap.entity.SnapLike

object DummySnapLike {
    private val id: Long = 1L

    private val dummySnapLike: SnapLike by lazy {
        SnapLike().also {
            it.id = id
        }
    }

    fun toEntity(): SnapLike {
        return dummySnapLike
    }
}
