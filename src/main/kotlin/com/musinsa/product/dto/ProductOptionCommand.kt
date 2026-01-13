package com.musinsa.product.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = CreateProductOptionRequest::class, name = "create"),
    JsonSubTypes.Type(value = UpdateProductOptionRequest::class, name = "update"),
)
sealed interface ProductOptionCommand
