package com.musinsa

import com.musinsa.common.properties.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class MusinsaApplication

fun main(args: Array<String>) {
    runApplication<MusinsaApplication>(*args)
}
