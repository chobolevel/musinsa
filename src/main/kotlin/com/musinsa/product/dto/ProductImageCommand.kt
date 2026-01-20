package com.musinsa.product.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = CreateProductImageRequest::class, name = "create"),
    JsonSubTypes.Type(value = UpdateProductImageRequest::class, name = "update"),
)
interface ProductImageCommand
