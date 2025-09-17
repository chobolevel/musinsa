package com.musinsa.application

import com.musinsa.application.entity.Application
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("application entity unit test")
class ApplicationEntityTest {

    private val dummyApplication: Application = DummyApplication.toEntity()

    @Test
    fun deleteTest() {
        // given

        // when
        dummyApplication.delete()

        // then
        Assertions.assertThat(dummyApplication.isDeleted).isTrue()
    }
}
