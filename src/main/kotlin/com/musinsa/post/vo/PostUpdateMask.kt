package com.musinsa.post.vo

enum class PostUpdateMask(val fieldName: String) {
    TAG("post_tag_ids"),
    TITLE("title"),
    CONTENT("content")
}
