package com.musinsa

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig

fun main() {
    val encryptor = PooledPBEStringEncryptor()
    val config = SimpleStringPBEConfig()
    // 암호화할 때 사용하는 키
    config.setPassword("musinsa")
    // 암호화 알고리즘
    config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256")
    // 암호화 횟수
    config.setKeyObtentionIterations("1000")
    // 인스턴스 pool
    config.setPoolSize("1")
    config.setProviderName("SunJCE")
    // salt 생성 클래스
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
    config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator")
    // 인코딩 방
    config.setStringOutputType("base64")
    encryptor.setConfig(config)
    println("encrypted: " + encryptor.encrypt("7S6SYkBosgRz+QxZvS0sI7ieizxVy7gaxsmTuQSD"))
}
