package com.musinsa.post.updater

import com.musinsa.post.dto.UpdatePostImageRequest
import com.musinsa.post.entity.PostImage
import com.musinsa.post.vo.PostImageUpdateMask
import org.springframework.stereotype.Component

@Component
class PostImageUpdater {

    fun markAsUpdate(
        request: UpdatePostImageRequest,
        postImage: PostImage
    ): PostImage {
        request.updateMask.forEach {
            when (it) {
                PostImageUpdateMask.TYPE -> postImage.type = request.type!!
                PostImageUpdateMask.PATH -> postImage.path = request.path!!
                PostImageUpdateMask.WIDTH -> postImage.width = request.width!!
                PostImageUpdateMask.HEIGHT -> postImage.height = request.height!!
                PostImageUpdateMask.SORT_ORDER -> postImage.sortOrder = request.sortOrder!!
            }
        }
        return postImage
    }
}
