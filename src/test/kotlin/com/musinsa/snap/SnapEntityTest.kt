package com.musinsa.snap

import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapTag
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

    @Test
    fun deleteSnapImageInBatchTest() {
        // given
        val dummySnapImageUrl = "dummyUrl"
        dummySnap.addSnapImage(
            url = dummySnapImageUrl,
            width = 0,
            height = 0,
            order = 0
        )

        // when
        dummySnap.deleteSnapImageInBatch()

        // then
        assertThat(dummySnap.snapImages).isEmpty()
    }

    @Test
    fun addSnapTagTest() {
        // given
        val dummySnapTag: SnapTag = DummySnapTag.toEntity()

        // when
        dummySnap.addSnapTag(snapTag = dummySnapTag)

        // then
        assertThat(dummySnap.snapTagMappings[0].snap).isEqualTo(dummySnap)
        assertThat(dummySnap.snapTagMappings[0].snapTag).isEqualTo(dummySnapTag)
    }

    @Test
    fun subSnapTagTest() {
        // given
        val dummySnapTag: SnapTag = DummySnapTag.toEntity()
        dummySnap.addSnapTag(snapTag = dummySnapTag)

        // when
        dummySnap.subSnapTag(snapTag = dummySnapTag)

        // then
        assertThat(dummySnap.snapTagMappings).isEmpty()
    }
}
