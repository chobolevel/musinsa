package com.musinsa.snap.like

import com.musinsa.snap.DummySnap
import com.musinsa.snap.entity.Snap
import com.musinsa.snap.entity.SnapLike
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("snap like entity unit test")
class SnapLikeEntityTest {

    private val dummySnapLike: SnapLike = DummySnapLike.toEntity()

    @Test
    fun assignSnapTest() {
        // given
        val dummySnap: Snap = DummySnap.toEntity()

        // when
        dummySnapLike.assignSnap(snap = dummySnap)

        // then
        assertThat(dummySnapLike.snap).isEqualTo(dummySnap)
    }

    @Test
    fun assignUserTest() {
        // given
        val dummyUser: User = DummyUser.toEntity()

        // when
        dummySnapLike.assignUser(user = dummyUser)

        // then
        assertThat(dummySnapLike.user).isEqualTo(dummyUser)
    }
}
