package com.musinsa.common.extension

import java.security.Principal
import java.time.OffsetDateTime

fun OffsetDateTime.toTimeStamp(): Long {
    return this.toInstant().toEpochMilli()
}

fun Principal.getUserId(): Long {
    return this.name.toLong()
}

fun OffsetDateTime.toMillis(): Long {
    return this.toInstant().toEpochMilli()
}
