package com.musinsa.snap.image

import com.musinsa.snap.DummySnap
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapImage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("snap image entity unit test")
class SnapImageEntityTest {

    private val dummySnapImage: SnapImage = DummySnapImage.toEntity()

    @Test
    fun assignSnapTest() {
        // given
        val dummySnap: Snap = DummySnap.toEntity()

        // when
        dummySnapImage.assignSnap(snap = dummySnap)

        // then
        assertThat(dummySnapImage.snap).isEqualTo(dummySnap)
    }

    @Test
    fun deleteSnapTest() {
        // given

        // when
        dummySnapImage.delete()

        // then
        assertThat(dummySnapImage.isDeleted).isTrue()
    }
}
