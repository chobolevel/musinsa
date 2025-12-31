package com.musinsa.snap.tag

import com.musinsa.snap.DummySnap
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapTag
import com.musinsa.snap.entity.SnapTagMapping
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("snap tag mapping entity unit test")
class SnapTagMappingEntityTest {

    private val dummySnapTagMapping: SnapTagMapping = DummySnapTagMapping.toEntity()

    @Test
    fun assignSnapTest() {
        // given
        val dummySnap: Snap = DummySnap.toEntity()

        // when
        dummySnapTagMapping.assignSnap(snap = dummySnap)

        // then
        assertThat(dummySnapTagMapping.snap).isEqualTo(dummySnap)
    }

    @Test
    fun assignSnapTagTest() {
        // given
        val dummySnapTag: SnapTag = DummySnapTag.toEntity()

        // when
        dummySnapTagMapping.assignSnapTag(snapTag = dummySnapTag)

        // then
        assertThat(dummySnapTagMapping.snapTag).isEqualTo(dummySnapTag)
    }
}
