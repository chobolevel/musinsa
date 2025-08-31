package com.musinsa.common.extension

import java.time.OffsetDateTime

fun OffsetDateTime.toTimeStamp(): Long {
    return this.toInstant().toEpochMilli()
}
