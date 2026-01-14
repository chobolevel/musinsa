package com.musinsa.snap.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = CreateSnapImageRequest::class, name = "create"),
    JsonSubTypes.Type(value = UpdateSnapImageRequest::class, name = "update"),
)
sealed interface SnapImageCommand
