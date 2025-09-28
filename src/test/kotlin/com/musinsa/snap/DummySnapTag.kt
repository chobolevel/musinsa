package com.musinsa.snap

import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.vo.SnapTagType

object DummySnapTag {
    private val id: Long = 1L
    private val type: SnapTagType = SnapTagType.STYLE
    private val name: String = "캐주얼"
    private val createdAt: Long = 0L
    private val updatedAt: Long = 0L

    private val dummySnapTag: SnapTag = SnapTag(
        type = type,
        name = name,
    ).also {
        it.id = id
    }

    fun toEntity(): SnapTag {
        return dummySnapTag
    }
}
