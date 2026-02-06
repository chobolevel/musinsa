package com.musinsa.post.updater

import com.musinsa.post.dto.UpdatePostRequest
import com.musinsa.post.entity.Post
import com.musinsa.post.entity.PostTag
import com.musinsa.post.reader.PostTagReader
import com.musinsa.post.vo.PostUpdateMask
import org.springframework.stereotype.Component

@Component
class PostUpdater(
    private val postTagReader: PostTagReader
) {

    fun markAsUpdate(
        request: UpdatePostRequest,
        post: Post
    ): Post {
        request.updateMask.forEach {
            when (it) {
                PostUpdateMask.TAG -> {
                    val savedPostTagMap: Map<Long, PostTag> = post.postTagMappings.map { it.postTag!! }.associateBy { it.id!! }

                    request.postTagIds?.forEach { postTagId ->
                        val savedPostTag: PostTag? = savedPostTagMap[postTagId]

                        if (savedPostTag == null) {
                            val postTag: PostTag = postTagReader.findById(id = postTagId)
                            post.addPostTag(postTag = postTag)
                        }
                    }

                    val deletedPostTagIds: Set<Long> = post.postTagMappings.filter { it.postTag!!.id!! !in (request.postTagIds ?: emptySet()) }.map { it.id!! }.toSet()
                    post.deletePostTagInBatch(postTagIds = deletedPostTagIds)
                }
                PostUpdateMask.TITLE -> post.title = request.title!!
                PostUpdateMask.CONTENT -> post.content = request.content!!
            }
        }
        return post
    }
}
