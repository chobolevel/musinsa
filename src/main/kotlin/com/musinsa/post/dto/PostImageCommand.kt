package com.musinsa.post.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = CreatePostImageRequest::class, name = "create"),
    JsonSubTypes.Type(value = UpdatePostImageRequest::class, name = "update")
)
interface PostImageCommand
