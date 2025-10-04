package com.musinsa.snap

import com.musinsa.snap.entity.Snap
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("snap entity unit test")
class SnapEntityTest {

    private val dummySnap: Snap = DummySnap.toEntity()

    @Test
    fun mappingWriterTest() {
        // given
        val dummyUser: User = DummyUser.toEntity()

        // when
        dummySnap.assignWriter(user = dummyUser)

        // then
        assertThat(dummySnap.writer).isEqualTo(dummyUser)
    }

    @Test
    fun deleteTest() {
        // given

        // when
        dummySnap.delete()

        // then
        assertThat(dummySnap.isDeleted).isTrue()
    }

    @Test
    fun addSnapImageTest() {
        // given
        val dummySnapImageUrl = "dummyUrl"

        // when
        dummySnap.addSnapImage(
            url = dummySnapImageUrl,
            width = 0,
            height = 0,
            order = 0
        )

        // then
        assertThat(dummySnap.snapImages[0].url).isEqualTo(dummySnapImageUrl)
    }
}
