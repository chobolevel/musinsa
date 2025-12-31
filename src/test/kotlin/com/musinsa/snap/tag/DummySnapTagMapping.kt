package com.musinsa.snap.tag

import com.musinsa.snap.entity.SnapTagMapping

object DummySnapTagMapping {
    private val id: Long = 1L
    private val order: Int = 0

    private val dummySnapTagMapping: SnapTagMapping by lazy {
        SnapTagMapping(order = order)
    }

    fun toEntity() = dummySnapTagMapping
}
