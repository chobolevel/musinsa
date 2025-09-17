package com.musinsa.application.util

import java.security.SecureRandom
import java.util.UUID

object ApplicationCredentialGenerator {

    private val secureRandom = SecureRandom()
    private val base62Chars = "ajsfawkdosagh12k4nt19g9iqn9bvs".toCharArray()

    /**
     * key 생성
     * - UUID v4 기반
     */
    fun generateApplicationKey(): String {
        return UUID.randomUUID().toString()
    }

    /**
     * secret_key 생성
     * - SecureRandom 기반 32바이트 랜덤 값
     * - URL-safe 문자열(Base62)로 인코딩
     */
    fun generateApplicationSecretKey(length: Int = 32): String {
        val sb = StringBuilder()
        repeat(length) {
            sb.append(base62Chars[secureRandom.nextInt(base62Chars.size)])
        }
        return sb.toString()
    }
}
