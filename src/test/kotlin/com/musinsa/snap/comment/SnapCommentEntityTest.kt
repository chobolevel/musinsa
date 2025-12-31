package com.musinsa.snap.comment

import com.musinsa.snap.DummySnap
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapComment
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("snap comment entity unit test")
class SnapCommentEntityTest {

    private val dummySnapComment: SnapComment = DummySnapComment.toEntity()

    @Test
    fun assignParentTest() {
        // given
        val dummyParentSnapComment = DummySnapComment.toParentEntity()

        // when
        dummySnapComment.assignParent(snapComment = dummyParentSnapComment)

        // then
        assertThat(dummySnapComment.parent).isEqualTo(dummyParentSnapComment)
    }

    @Test
    fun assignSnapTest() {
        // given
        val dummySnap: Snap = DummySnap.toEntity()

        // when
        dummySnapComment.assignSnap(snap = dummySnap)

        // then
        assertThat(dummySnapComment.snap).isEqualTo(dummySnap)
    }

    @Test
    fun assignWriterTest() {
        // given
        val dummyUser: User = DummyUser.toEntity()

        // when
        dummySnapComment.assignWriter(user = dummyUser)

        // then
        assertThat(dummySnapComment.writer).isEqualTo(dummyUser)
    }

    @Test
    fun deleteTest() {
        // given

        // when
        dummySnapComment.delete()

        // then
        assertThat(dummySnapComment.isDeleted).isTrue()
    }
}
