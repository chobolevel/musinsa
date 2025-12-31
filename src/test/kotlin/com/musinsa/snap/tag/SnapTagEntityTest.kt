package com.musinsa.snap.tag

import com.musinsa.snap.entity.SnapTag
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("snap tags entity unit test")
class SnapTagEntityTest {

    private val dummySnapTag: SnapTag = DummySnapTag.toEntity()

    @Test
    fun deleteTest() {
        // given

        // when
        dummySnapTag.delete()

        // then
        assertThat(dummySnapTag.isDeleted).isTrue()
    }
}
