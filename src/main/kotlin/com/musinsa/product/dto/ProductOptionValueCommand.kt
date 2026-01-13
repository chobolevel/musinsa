package com.musinsa.product.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = CreateProductOptionValueRequest::class, name = "create"),
    JsonSubTypes.Type(value = UpdateProductOptionValueRequest::class, name = "update"),
)
sealed interface ProductOptionValueCommand
