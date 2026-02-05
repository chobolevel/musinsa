package com.musinsa.post.updater

import com.musinsa.post.dto.UpdatePostTagRequest
import com.musinsa.post.entity.PostTag
import com.musinsa.post.vo.PostTagUpdateMask
import org.springframework.stereotype.Component

@Component
class PostTagUpdater {

    fun markAsUpdate(request: UpdatePostTagRequest, postTag: PostTag): PostTag {
        request.updateMask.forEach {
            when (it) {
                PostTagUpdateMask.NAME -> postTag.name = request.name!!
                PostTagUpdateMask.SORT_ORDER -> postTag.sortOrder = request.sortOrder!!
            }
        }
        return postTag
    }
}
