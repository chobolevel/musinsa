package com.musinsa.post.updater

import com.musinsa.post.converter.PostImageConverter
import com.musinsa.post.dto.CreatePostImageRequest
import com.musinsa.post.dto.UpdatePostImageRequest
import com.musinsa.post.dto.UpdatePostRequest
import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostImage
import com.musinsa.post.entity.PostTag
import com.musinsa.post.reader.PostTagReader
import com.musinsa.post.vo.PostUpdateMask
import org.springframework.stereotype.Component

@Component
class PostUpdater(
    private val postTagReader: PostTagReader,
    private val postImageConverter: PostImageConverter,
    private val postImageUpdater: PostImageUpdater
) {

    fun markAsUpdate(
        request: UpdatePostRequest,
        post: Post
    ): Post {
        request.updateMask.forEach {
            when (it) {
                PostUpdateMask.TAG -> {
                    val deletedPostTagIds: Set<Long> = post.postTagMappings.filter { it.postTag!!.id!! !in (request.postTagIds ?: emptySet()) }.map { it.id!! }.toSet()
                    post.deletePostTagInBatch(postTagIds = deletedPostTagIds)

                    val savedPostTagMap: Map<Long, PostTag> = post.postTagMappings.map { it.postTag!! }.associateBy { it.id!! }

                    request.postTagIds?.forEach { postTagId ->
                        val savedPostTag: PostTag? = savedPostTagMap[postTagId]

                        if (savedPostTag == null) {
                            val postTag: PostTag = postTagReader.findById(id = postTagId)
                            post.addPostTag(postTag = postTag)
                        }
                    }
                }
                PostUpdateMask.TITLE -> post.title = request.title!!
                PostUpdateMask.CONTENT -> post.content = request.content!!
                PostUpdateMask.IMAGES -> {
                    val requestIds: Set<Long> = request.postImages?.filterIsInstance<UpdatePostImageRequest>()?.map { it.id }?.toSet() ?: emptySet()
                    val deletedPostImageIds: Set<Long> = post.postImages.filter { it.id !in requestIds }.map { it.id!! }.toSet()
                    post.deletePostImageInBatch(postImageIds = deletedPostImageIds.toList())

                    val savedPostImageMap: Map<Long, PostImage> = post.postImages.associateBy { it.id!! }
                    request.postImages?.forEach { request ->
                        when (request) {
                            is CreatePostImageRequest -> {
                                val postImage: PostImage = postImageConverter.toEntity(request = request)
                                post.addPostImage(postImage = postImage)
                            }
                            is UpdatePostImageRequest -> {
                                val savedPostImage: PostImage? = savedPostImageMap[request.id]
                                savedPostImage?.let {
                                    postImageUpdater.markAsUpdate(
                                        request = request,
                                        postImage = it
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        return post
    }
}
