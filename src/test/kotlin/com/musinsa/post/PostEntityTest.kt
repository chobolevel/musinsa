package com.musinsa.post

import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostImage
import com.musinsa.post.entity.PostTag
import com.musinsa.post.image.DummyPostImage
import com.musinsa.post.tag.DummyPostTag
import com.musinsa.user.DummyUser
import com.musinsa.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

@DisplayName("psot entity unit test")
class PostEntityTest {

    private val dummyPost: Post = DummyPost.toEntity()

    private val dummyPostImage: PostImage = DummyPostImage.toEntity()

    @Test
    fun assignUserTest() {
        // given
        val dummyUser: User = DummyUser.toEntity()

        // when
        dummyPost.assignUser(user = dummyUser)

        // then
        assertThat(dummyPost.user).isEqualTo(dummyUser)
    }

    @Test
    fun deleteTest() {
        // given & when
        dummyPost.delete()

        // then
        assertThat(dummyPost.isDeleted).isTrue()
    }

    @Test
    fun addPostTagTest() {
        // given
        val dummyPostTag: PostTag = DummyPostTag.toEntity()

        // when
        dummyPost.addPostTag(postTag = dummyPostTag)

        // then
        assertThat(dummyPost.postTagMappings.first().postTag).isEqualTo(dummyPostTag)
    }

    @Test
    fun addPostImage() {
        // given & when
        dummyPost.addPostImage(postImage = dummyPostImage)

        // then
        assertThat(dummyPost.postImages.contains(dummyPostImage)).isTrue()
    }

    @Test
    fun addPostImageInBatch() {
        // given
        val dummyPostImages: List<PostImage> = listOf(dummyPostImage)

        // when
        dummyPost.addPostImageInBatch(postImages = dummyPostImages)

        // then
        assertThat(dummyPostImages.contains(dummyPostImage)).isTrue()
    }

    @Test
    fun removePostImageInBatchTest() {
        // given
        val dummyPostImageIds: List<Long> = listOf(dummyPostImage.id!!)
        dummyPost.addPostImage(postImage = dummyPostImage)

        // when
        dummyPost.deletePostImageInBatch(postImageIds = dummyPostImageIds)

        // then
        assertThat(dummyPost.postImages.isEmpty()).isTrue()
    }
}
